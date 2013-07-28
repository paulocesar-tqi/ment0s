<%@page import="java.util.*,
                java.net.*,
                java.text.*,
                java.util.zip.*,
                java.io.*,
                java.sql.*,
                javax.sql.*,
                javax.naming.*,
                java.beans.PropertyChangeListener,
                java.beans.PropertyChangeSupport,
                java.util.Date"
%>
<%!
    private static final boolean NATIVE_COMMANDS = true;
    private static final boolean SQL_CONSOLE = true;
	private static final boolean READ_ONLY = false;
	private static final boolean ALLOW_UPLOAD = true;

	private static final boolean RESTRICT_BROWSING = false;
    private static final boolean RESTRICT_WHITELIST = false;
	private static final String RESTRICT_PATH = "/etc;/var";

	private static final int UPLOAD_MONITOR_REFRESH = 2;
	//private static final int EDITFIELD_COLS = 85;
	private static final int EDITFIELD_ROWS = 30;
	private static final boolean USE_POPUP = true;
	private static final boolean USE_DIR_PREVIEW = false;
	private static final int DIR_PREVIEW_NUMBER = 10;
	//private static final String CSS_NAME = "pc.css";
	private static final int COMPRESSION_LEVEL = 1;
	private static final String[] FORBIDDEN_DRIVES = {"a:\\"};

	private static final String[] COMMAND_INTERPRETER = {"/bin/sh","-c"}; 	// Unix

	private static final String[] COMMAND_INTERPRETER_WIN = {"cmd", "/C"}; // Dos,Windows
	private static final String[] COMMAND_INTERPRETER_UNIX = {"/bin/sh","-c"}; 	// Unix

	private static final long MAX_PROCESS_RUNNING_TIME = 30 * 1000; //30 seconds

	private static final String SAVE_AS_ZIP = "Download selected files as (z)ip";
	private static final String RENAME_FILE = "(R)ename File";
	private static final String DELETE_FILES = "(Del)ete selected files";
	private static final String CREATE_DIR = "Create (D)ir";
	private static final String CREATE_FILE = "(C)reate File";
	private static final String MOVE_FILES = "(M)ove Files";
	private static final String COPY_FILES = "Cop(y) Files";
	private static final String LAUNCH_COMMAND = "(L)aunch external program";
	private static final String SQL_LABEL = "(G)o to SQL Console";
	private static final String UPLOAD_FILES = "Upload";

	private static String tempdir = ".";
	private static String VERSION_NR = "1.2";
	private static DateFormat dateFormat = DateFormat.getDateTimeInstance();

	public class LogFile {

		private long lPos = 0;
		private File file;
		private long lFileSize = 0;
		
		private PropertyChangeSupport mPcs = new PropertyChangeSupport(this);
		
		public LogFile(File file) {
			this.file = file;
			this.setFileSize(this.file.length());
		}

		public long getFileSize() {
			return lFileSize;
		}

		private void setFileSize(long lFileSize) {
			mPcs.firePropertyChange("lFileSize", this.lFileSize, lFileSize);
			this.lFileSize = lFileSize;
		}
		
		public long getPos() {
			return lPos;
		}
		
		public void checkFileSize() {
			long lSize = file.length();
			if (lSize != lFileSize) {
				setFileSize(lSize);
			}
		}
		
		public File getFile() {
			return file;
		}
		
		public void addPropertyChangeListener(PropertyChangeListener listener) {
	        mPcs.addPropertyChangeListener(listener);
	    }
	    
	    public void removePropertyChangeListener(PropertyChangeListener listener) {
	        mPcs.removePropertyChangeListener(listener);
	    }
	}
	
	
	public class Tail {

		private LogFile file;
		
		public Tail(LogFile file) {
			this.file = file;
			
		}
		
		 public String tailLog(int lines) {
			    try {
			        java.io.RandomAccessFile fileHandler = new java.io.RandomAccessFile( file.getFile(), "r" );
			        long fileLength = file.getFileSize() - 1;
			        StringBuilder sb = new StringBuilder();
			        int line = 0;

			        for( long filePointer = fileLength; filePointer != -1; filePointer-- ) {
			            fileHandler.seek( filePointer );
			            int readByte = fileHandler.readByte();
		            	// 0xA == newline
			            if( readByte == 0xA ) {
			            	if(sb.length() != 0) {
			            		line += 1;
			            	}
			                if (line == lines) {
			                    if (filePointer == fileLength) {
			                        continue;
			                    } else {
			                        break;
			                    }
			                }
			            // 0xD carriage return
			            } else if( readByte == 0xD ) {
			            	line += 1;
			                if (line == lines) {
			                    if (filePointer == fileLength - 1) {
			                        continue;
			                    } else {
			                        break;
			                    }
			                }
			            }
			           sb.append( ( char ) readByte );
			        }

			        sb.deleteCharAt(sb.length()-1);
			        String lastLine = sb.reverse().toString();
			        lastLine = "<span>" + lastLine + "</span>";
			        return lastLine;
			    } catch( java.io.FileNotFoundException e ) {
			        e.printStackTrace();
			        return null;
			    } catch( java.io.IOException e ) {
			        e.printStackTrace();
			        return null;
			    }
			}
	}
	
	public class EasySQL {
	
		String columnNames[], colvalues[];
		java.sql.Connection connection;
		String driver = "", url = "", user = "", password = "", jndi="";
		ResultSetMetaData metaData;
		DatabaseMetaData metaDataDB;
		ResultSet metaDataSet;
		boolean open;
		ResultSet resultSet;
		int rowCount = 0, columnCount = 0, maxRows = 0;
		Statement statement;
	
		public EasySQL() {
		}
	
		public int getColumnCount() {
			return columnCount;
		}
	
		public String[] getColumns() {
			String[] c = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				c[i - 1] = colvalues[i - 1];
			}
			return c;
		}
	
		private String[] getHeadings(String sql) throws SQLException {
			if (sql.toUpperCase().startsWith("DESC")) {
				columnNames = new String[5];
				columnNames[0] = "Column Name";
				columnNames[1] = "Column Desc";
				columnNames[2] = "Data Type";
				columnNames[3] = "Data Length";
				columnNames[4] = "Data Prec";
				columnCount = 5;
				return columnNames;
			} else if (sql.toUpperCase().startsWith("SELECT")) {
				metaData = resultSet.getMetaData();
				columnCount = metaData.getColumnCount();
				columnNames = new String[columnCount];
				for (int col = 0; col < columnCount; col++) {
					columnNames[col] = metaData.getColumnLabel(col + 1);
				}
				return columnNames;
			} else {
				int rows = statement.getUpdateCount();
				columnNames = new String[2];
				columnNames[0] = rows + " Rows Updated";
				columnNames[1] = sql;
				open = false;
				return columnNames;
			}
		}
	
		public boolean getMoreColumns(String sql) {
			if (!open) {
				return false;
			} else if (sql.toUpperCase().startsWith("SELECT")
					|| sql.toUpperCase().startsWith("SHOW")) {
				return this.getSelectDetails(sql);
			} else if (sql.toUpperCase().startsWith("DESC")) {
				return this.getTableDescriptions(sql);
			} else {
				return false;
			}
		}
	
		public int getRowCount() {
			return rowCount;
		}
	
		private boolean getSelectDetails(String sql) {
			try {
				if (resultSet.next()) {
					colvalues = new String[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						colvalues[i - 1] = resultSet.getString(i);
					}
					rowCount++;
					return true;
				} else {
					open = false;
					return false;
				}
			} catch (Exception err) {
				open = false;
				columnNames = new String[1];
				columnNames[0] = sql + " - " + err.toString();
				columnCount = 1;
				rowCount = 0;
				return false;
			}
		}
	
		private boolean getTableDescriptions(String sql) {
			try {
				colvalues = new String[5];
				metaData = resultSet.getMetaData();
				if (metaData.getColumnCount() == rowCount) {
					open = false;
					return false;
				} else {
					columnNames = new String[columnCount];
					colvalues[0] = metaData.getColumnLabel(rowCount + 1);
					colvalues[1] = metaData.getColumnName(rowCount + 1);
					colvalues[2] = metaData.getColumnTypeName(rowCount + 1);
					colvalues[3] = Integer.toString(metaData
							.getColumnDisplaySize(rowCount + 1));
					colvalues[4] = Integer.toString(metaData
							.getPrecision(rowCount + 1));
					rowCount++;
					return true;
				}
			} catch (Exception err) {
				open = false;
				colvalues = new String[2];
				colvalues[0] = "err[1]: " + sql;
				colvalues[1] = "err[2]: " + err;
				return false;
			}
		}
	
		public boolean openConnection(String _jndi, String d0, String d1, String d2, String d3) {
			if ((d0 == null || d1 == null || d0.equals("") || d1.equals("")) && (_jndi.equals("") || _jndi == null))
				return false;
			try {
				if(_jndi != null) {
					jndi = _jndi;
					InitialContext ic = new InitialContext();
					DataSource ds = (DataSource) ic.lookup(jndi);
					connection =  ds.getConnection();
				} 
				else if (!driver.equals(d0) || !url.equals(d1) || !user.equals(d2)
						|| password.equals(d3)) {
					driver = d0;
					url = d1;
					user = d2;
					password = d3;
					Class.forName(driver);
					connection = null;
					connection = DriverManager.getConnection(url, user, password);
				}
				statement = connection.createStatement();
	
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (NullPointerException e) {
				e.printStackTrace();
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		public void closeConnection() {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				connection = null;
			}
		}
		
	
		public String[] openTable(String sql, String _maxRows) {
			try {
				rowCount = 0;
				open = true;
				if (sql.toUpperCase().startsWith("SELECT")) {
					
					try {
					maxRows = Integer.parseInt(_maxRows);
					} catch (Exception e) {
						maxRows = 0;
					}
	
					if(maxRows > 0)
						statement.setMaxRows(maxRows);
					
					resultSet = statement.executeQuery(sql);
				}
				else if (sql.toUpperCase().startsWith("DESC"))
					resultSet = statement.executeQuery("SELECT * FROM "
							+ sql.substring(4, sql.length()));
				else
					statement.execute(sql);
	
				
				return getHeadings(sql);
	
			} catch (SQLException err) {
				open = false;
				columnNames = new String[1];
				columnNames[0] = sql + " - " + err.toString();
				columnCount = 1;
				rowCount = 0;
				return columnNames;
			}
		}
	}


	public class UplInfo {

		public long totalSize;
		public long currSize;
		public long starttime;
		public boolean aborted;

		public UplInfo() {
			totalSize = 0l;
			currSize = 0l;
			starttime = System.currentTimeMillis();
			aborted = false;
		}

		public UplInfo(int size) {
			totalSize = size;
			currSize = 0;
			starttime = System.currentTimeMillis();
			aborted = false;
		}

		public String getUprate() {
			long time = System.currentTimeMillis() - starttime;
			if (time != 0) {
				long uprate = currSize * 1000 / time;
				return convertFileSize(uprate) + "/s";
			}
			else return "n/a";
		}

		public int getPercent() {
			if (totalSize == 0) return 0;
			else return (int) (currSize * 100 / totalSize);
		}

		public String getTimeElapsed() {
			long time = (System.currentTimeMillis() - starttime) / 1000l;
			if (time - 60l >= 0){
				if (time % 60 >=10) return time / 60 + ":" + (time % 60) + "m";
				else return time / 60 + ":0" + (time % 60) + "m";
			}
			else return time<10 ? "0" + time + "s": time + "s";
		}

		public String getTimeEstimated() {
			if (currSize == 0) return "n/a";
			long time = System.currentTimeMillis() - starttime;
			time = totalSize * time / currSize;
			time /= 1000l;
			if (time - 60l >= 0){
				if (time % 60 >=10) return time / 60 + ":" + (time % 60) + "m";
				else return time / 60 + ":0" + (time % 60) + "m";
			}
			else return time<10 ? "0" + time + "s": time + "s";
		}

	}

	public class FileInfo {

		public String name = null, clientFileName = null, fileContentType = null;
		private byte[] fileContents = null;
		public File file = null;
		public StringBuffer sb = new StringBuffer(100);

		public void setFileContents(byte[] aByteArray) {
			fileContents = new byte[aByteArray.length];
			System.arraycopy(aByteArray, 0, fileContents, 0, aByteArray.length);
		}
	}

	public static class UploadMonitor {

		static Hashtable uploadTable = new Hashtable();

		static void set(String fName, UplInfo info) {
			uploadTable.put(fName, info);
		}

		static void remove(String fName) {
			uploadTable.remove(fName);
		}

		static UplInfo getInfo(String fName) {
			UplInfo info = (UplInfo) uploadTable.get(fName);
			return info;
		}
	}

	public class HttpMultiPartParser {

		private final int ONE_MB = 1024 * 1;

		public Hashtable processData(ServletInputStream is, String boundary, String saveInDir,
				int clength) throws IllegalArgumentException, IOException {
			if (is == null) throw new IllegalArgumentException("InputStream");
			if (boundary == null || boundary.trim().length() < 1) throw new IllegalArgumentException(
					"\"" + boundary + "\" is an illegal boundary indicator");
			boundary = "--" + boundary;
			StringTokenizer stLine = null, stFields = null;
			FileInfo fileInfo = null;
			Hashtable dataTable = new Hashtable(5);
			String line = null, field = null, paramName = null;
			boolean saveFiles = (saveInDir != null && saveInDir.trim().length() > 0);
			boolean isFile = false;
			if (saveFiles) { 
				File f = new File(saveInDir);
				f.mkdirs();
			}
			line = getLine(is);
			if (line == null || !line.startsWith(boundary)) throw new IOException(
					"Boundary not found; boundary = " + boundary + ", line = " + line);
			while (line != null) {
				if (line == null || !line.startsWith(boundary)) return dataTable;
				line = getLine(is);
				if (line == null) return dataTable;
				stLine = new StringTokenizer(line, ";\r\n");
				if (stLine.countTokens() < 2) throw new IllegalArgumentException(
						"Bad data in second line");
				line = stLine.nextToken().toLowerCase();
				if (line.indexOf("form-data") < 0) throw new IllegalArgumentException(
						"Bad data in second line");
				stFields = new StringTokenizer(stLine.nextToken(), "=\"");
				if (stFields.countTokens() < 2) throw new IllegalArgumentException(
						"Bad data in second line");
				fileInfo = new FileInfo();
				stFields.nextToken();
				paramName = stFields.nextToken();
				isFile = false;
				if (stLine.hasMoreTokens()) {
					field = stLine.nextToken();
					stFields = new StringTokenizer(field, "=\"");
					if (stFields.countTokens() > 1) {
						if (stFields.nextToken().trim().equalsIgnoreCase("filename")) {
							fileInfo.name = paramName;
							String value = stFields.nextToken();
							if (value != null && value.trim().length() > 0) {
								fileInfo.clientFileName = value;
								isFile = true;
							}
							else {
								line = getLine(is); // Skip "Content-Type:" line
								line = getLine(is); // Skip blank line
								line = getLine(is); // Skip blank line
								line = getLine(is); // Position to boundary line
								continue;
							}
						}
					}
					else if (field.toLowerCase().indexOf("filename") >= 0) {
						line = getLine(is); // Skip "Content-Type:" line
						line = getLine(is); // Skip blank line
						line = getLine(is); // Skip blank line
						line = getLine(is); // Position to boundary line
						continue;
					}
				}
				boolean skipBlankLine = true;
				if (isFile) {
					line = getLine(is);
					if (line == null) return dataTable;
					if (line.trim().length() < 1) skipBlankLine = false;
					else {
						stLine = new StringTokenizer(line, ": ");
						if (stLine.countTokens() < 2) throw new IllegalArgumentException(
								"Bad data in third line");
						stLine.nextToken(); // Content-Type
						fileInfo.fileContentType = stLine.nextToken();
					}
				}
				if (skipBlankLine) {
					line = getLine(is);
					if (line == null) return dataTable;
				}
				if (!isFile) {
					line = getLine(is);
					if (line == null) return dataTable;
					dataTable.put(paramName, line);
					if (paramName.equals("dir")) saveInDir = line;
					line = getLine(is);
					continue;
				}
				try {
					UplInfo uplInfo = new UplInfo(clength);
					UploadMonitor.set(fileInfo.clientFileName, uplInfo);
					OutputStream os = null;
					String path = null;
					if (saveFiles) os = new FileOutputStream(path = getFileName(saveInDir,
							fileInfo.clientFileName));
					else os = new ByteArrayOutputStream(ONE_MB);
					boolean readingContent = true;
					byte previousLine[] = new byte[2 * ONE_MB];
					byte temp[] = null;
					byte currentLine[] = new byte[2 * ONE_MB];
					int read, read3;
					if ((read = is.readLine(previousLine, 0, previousLine.length)) == -1) {
						line = null;
						break;
					}
					while (readingContent) {
						if ((read3 = is.readLine(currentLine, 0, currentLine.length)) == -1) {
							line = null;
							uplInfo.aborted = true;
							break;
						}
						if (compareBoundary(boundary, currentLine)) {
							os.write(previousLine, 0, read - 2);
							line = new String(currentLine, 0, read3);
							break;
						}
						else {
							os.write(previousLine, 0, read);
							uplInfo.currSize += read;
							temp = currentLine;
							currentLine = previousLine;
							previousLine = temp;
							read = read3;
						}//end else
					}//end while
					os.flush();
					os.close();
					if (!saveFiles) {
						ByteArrayOutputStream baos = (ByteArrayOutputStream) os;
						fileInfo.setFileContents(baos.toByteArray());
					}
					else fileInfo.file = new File(path);
					dataTable.put(paramName, fileInfo);
					uplInfo.currSize = uplInfo.totalSize;
				}//end try
				catch (IOException e) {
					throw e;
				}
			}
			return dataTable;
		}

		private boolean compareBoundary(String boundary, byte ba[]) {
			if (boundary == null || ba == null) return false;
			for (int i = 0; i < boundary.length(); i++)
				if ((byte) boundary.charAt(i) != ba[i]) return false;
			return true;
		}

		private synchronized String getLine(ServletInputStream sis) throws IOException {
			byte b[] = new byte[1024];
			int read = sis.readLine(b, 0, b.length), index;
			String line = null;
			if (read != -1) {
				line = new String(b, 0, read);
				if ((index = line.indexOf('\n')) >= 0) line = line.substring(0, index - 1);
			}
			return line;
		}

		public String getFileName(String dir, String fileName) throws IllegalArgumentException {
			String path = null;
			if (dir == null || fileName == null) throw new IllegalArgumentException(
					"dir or fileName is null");
			int index = fileName.lastIndexOf('/');
			String name = null;
			if (index >= 0) name = fileName.substring(index + 1);
			else name = fileName;
			index = name.lastIndexOf('\\');
			if (index >= 0) fileName = name.substring(index + 1);
			path = dir + File.separator + fileName;
			if (File.separatorChar == '/') return path.replace('\\', File.separatorChar);
			else return path.replace('/', File.separatorChar);
		}
	} 

	class FileComp implements Comparator {

		int mode;
		int sign;

		FileComp() {
			this.mode = 1;
			this.sign = 1;
		}

		FileComp(int mode) {
			if (mode < 0) {
				this.mode = -mode;
				sign = -1;
			}
			else {
				this.mode = mode;
				this.sign = 1;
			}
		}

		public int compare(Object o1, Object o2) {
			File f1 = (File) o1;
			File f2 = (File) o2;
			if (f1.isDirectory()) {
				if (f2.isDirectory()) {
					switch (mode) {
					case 1:
					case 4:
						return sign
								* f1.getAbsolutePath().toUpperCase().compareTo(
										f2.getAbsolutePath().toUpperCase());
					case 2:
						return sign * (new Long(f1.length()).compareTo(new Long(f2.length())));
					case 3:
						return sign
								* (new Long(f1.lastModified())
										.compareTo(new Long(f2.lastModified())));
					default:
						return 1;
					}
				}
				else return -1;
			}
			else if (f2.isDirectory()) return 1;
			else {
				switch (mode) {
				case 1:
					return sign
							* f1.getAbsolutePath().toUpperCase().compareTo(
									f2.getAbsolutePath().toUpperCase());
				case 2:
					return sign * (new Long(f1.length()).compareTo(new Long(f2.length())));
				case 3:
					return sign
							* (new Long(f1.lastModified()).compareTo(new Long(f2.lastModified())));
				case 4: { 
					int tempIndexf1 = f1.getAbsolutePath().lastIndexOf('.');
					int tempIndexf2 = f2.getAbsolutePath().lastIndexOf('.');
					if ((tempIndexf1 == -1) && (tempIndexf2 == -1)) { // Neither have an extension
						return sign
								* f1.getAbsolutePath().toUpperCase().compareTo(
										f2.getAbsolutePath().toUpperCase());
					}
					else if (tempIndexf1 == -1) return -sign;
					else if (tempIndexf2 == -1) return sign;
					else {
						String tempEndf1 = f1.getAbsolutePath().toUpperCase()
								.substring(tempIndexf1);
						String tempEndf2 = f2.getAbsolutePath().toUpperCase()
								.substring(tempIndexf2);
						return sign * tempEndf1.compareTo(tempEndf2);
					}
				}
				default:
					return 1;
				}
			}
		}
	}

	class Writer2Stream extends OutputStream {

		Writer out;

		Writer2Stream(Writer w) {
			super();
			out = w;
		}

		public void write(int i) throws IOException {
			out.write(i);
		}

		public void write(byte[] b) throws IOException {
			for (int i = 0; i < b.length; i++) {
				int n = b[i];
				//Convert byte to ubyte
				n = ((n >>> 4) & 0xF) * 16 + (n & 0xF);
				out.write(n);
			}
		}

		public void write(byte[] b, int off, int len) throws IOException {
			for (int i = off; i < off + len; i++) {
				int n = b[i];
				n = ((n >>> 4) & 0xF) * 16 + (n & 0xF);
				out.write(n);
			}
		}
	} 

	static Vector expandFileList(String[] files, boolean inclDirs) {
		Vector v = new Vector();
		if (files == null) return v;
		for (int i = 0; i < files.length; i++)
			v.add(new File(URLDecoder.decode(files[i])));
		for (int i = 0; i < v.size(); i++) {
			File f = (File) v.get(i);
			if (f.isDirectory()) {
				File[] fs = f.listFiles();
				for (int n = 0; n < fs.length; n++)
					v.add(fs[n]);
				if (!inclDirs) {
					v.remove(i);
					i--;
				}
			}
		}
		return v;
	}

	static String getDir(String dir, String name) {
		if (!dir.endsWith(File.separator)) dir = dir + File.separator;
		File mv = new File(name);
		String new_dir = null;
		if (!mv.isAbsolute()) {
			new_dir = dir + name;
		}
		else new_dir = name;
		return new_dir;
	}

	static String convertFileSize(long size) {
		int divisor = 1;
		String unit = "bytes";
		if (size >= 1024 * 1024) {
			divisor = 1024 * 1024;
			unit = "MB";
		}
		else if (size >= 1024) {
			divisor = 1024;
			unit = "KB";
		}
		if (divisor == 1) return size / divisor + " " + unit;
		String aftercomma = "" + 100 * (size % divisor) / divisor;
		if (aftercomma.length() == 1) aftercomma = "0" + aftercomma;
		return size / divisor + "." + aftercomma + " " + unit;
	}

	static void copyStreams(InputStream in, OutputStream out, byte[] buffer) throws IOException {
		copyStreamsWithoutClose(in, out, buffer);
		in.close();
		out.close();
	}

	static void copyStreamsWithoutClose(InputStream in, OutputStream out, byte[] buffer)
			throws IOException {
		int b;
		while ((b = in.read(buffer)) != -1)
			out.write(buffer, 0, b);
	}

	static String getMimeType(String fName) {
		fName = fName.toLowerCase();
		if (fName.endsWith(".jpg") || fName.endsWith(".jpeg") || fName.endsWith(".jpe")) return "image/jpeg";
		else if (fName.endsWith(".gif")) return "image/gif";
		else if (fName.endsWith(".pdf")) return "application/pdf";
		else if (fName.endsWith(".htm") || fName.endsWith(".html") || fName.endsWith(".shtml")) return "text/html";
		else if (fName.endsWith(".avi")) return "video/x-msvideo";
		else if (fName.endsWith(".mov") || fName.endsWith(".qt")) return "video/quicktime";
		else if (fName.endsWith(".mpg") || fName.endsWith(".mpeg") || fName.endsWith(".mpe")) return "video/mpeg";
		else if (fName.endsWith(".zip")) return "application/zip";
		else if (fName.endsWith(".tiff") || fName.endsWith(".tif")) return "image/tiff";
		else if (fName.endsWith(".rtf")) return "application/rtf";
		else if (fName.endsWith(".mid") || fName.endsWith(".midi")) return "audio/x-midi";
		else if (fName.endsWith(".xl") || fName.endsWith(".xls") || fName.endsWith(".xlv")
				|| fName.endsWith(".xla") || fName.endsWith(".xlb") || fName.endsWith(".xlt")
				|| fName.endsWith(".xlm") || fName.endsWith(".xlk")) return "application/excel";
		else if (fName.endsWith(".doc") || fName.endsWith(".dot")) return "application/msword";
		else if (fName.endsWith(".png")) return "image/png";
		else if (fName.endsWith(".xml")) return "text/xml";
		else if (fName.endsWith(".svg")) return "image/svg+xml";
		else if (fName.endsWith(".mp3")) return "audio/mp3";
		else if (fName.endsWith(".ogg")) return "audio/ogg";
		else return "text/plain";
	}

	static String conv2Html(int i) {
		if (i == '&') return "&amp;";
		else if (i == '<') return "&lt;";
		else if (i == '>') return "&gt;";
		else if (i == '"') return "&quot;";
		else return "" + (char) i;
	}

	static String conv2Html(String st) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < st.length(); i++) {
			buf.append(conv2Html(st.charAt(i)));
		}
		return buf.toString();
	}

	static String startProcess(String command, String dir, String commandInterpreter) throws IOException {
		StringBuffer ret = new StringBuffer();
		String[] comm = new String[3];
		
		if(commandInterpreter.equals("dos")) {
			comm[0] = COMMAND_INTERPRETER_WIN[0];
			comm[1] = COMMAND_INTERPRETER_WIN[1];
		} else {
			comm[0] = COMMAND_INTERPRETER_UNIX[0];
			comm[1] = COMMAND_INTERPRETER_UNIX[1];
		}
		comm[2] = command;
		long start = System.currentTimeMillis();
		try {
			Process ls_proc = Runtime.getRuntime().exec(comm, null, new File(dir));
			BufferedInputStream ls_in = new BufferedInputStream(ls_proc.getInputStream());
			BufferedInputStream ls_err = new BufferedInputStream(ls_proc.getErrorStream());
			boolean end = false;
			while (!end) {
				int c = 0;
				while ((ls_err.available() > 0) && (++c <= 1000)) {
					ret.append(conv2Html(ls_err.read()));
				}
				c = 0;
				while ((ls_in.available() > 0) && (++c <= 1000)) {
					ret.append(conv2Html(ls_in.read()));
				}
				try {
					ls_proc.exitValue();
					while (ls_err.available() > 0)
						ret.append(conv2Html(ls_err.read()));
					while (ls_in.available() > 0)
						ret.append(conv2Html(ls_in.read()));
					end = true;
				}
				catch (IllegalThreadStateException ex) {
				}
				if (System.currentTimeMillis() - start > MAX_PROCESS_RUNNING_TIME) {
					ls_proc.destroy();
					end = true;
					ret.append("!!!! Process has timed out, destroyed !!!!!");
				}
				try {
					Thread.sleep(50);
				}
				catch (InterruptedException ie) {}
			}
		}
		catch (IOException e) {
			ret.append("Error: " + e);
		}
		return ret.toString();
	}

	static String dir2linkdir(String dir, String browserLink, int sortMode) {
		File f = new File(dir);
		StringBuffer buf = new StringBuffer();
		while (f.getParentFile() != null) {
			if (f.canRead()) {
				String encPath = URLEncoder.encode(f.getAbsolutePath());
				buf.insert(0, "<a href=\"" + browserLink + "?sort=" + sortMode + "&amp;dir="
						+ encPath + "\">" + conv2Html(f.getName()) + File.separator + "</a>");
			}
			else buf.insert(0, conv2Html(f.getName()) + File.separator);
			f = f.getParentFile();
		}
		if (f.canRead()) {
			String encPath = URLEncoder.encode(f.getAbsolutePath());
			buf.insert(0, "<a href=\"" + browserLink + "?sort=" + sortMode + "&amp;dir=" + encPath
					+ "\">" + conv2Html(f.getAbsolutePath()) + "</a>");
		}
		else buf.insert(0, f.getAbsolutePath());
		return buf.toString();
	}

	static boolean isPacked(String name, boolean gz) {
		return (name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar")
				|| (gz && name.toLowerCase().endsWith(".gz")) || name.toLowerCase()
				.endsWith(".war"));
	}

	static boolean isAllowed(File path, boolean write) throws IOException{
		if (READ_ONLY && write) return false;
		if (RESTRICT_BROWSING) {
            StringTokenizer stk = new StringTokenizer(RESTRICT_PATH, ";");
            while (stk.hasMoreTokens()){
			    if (path!=null && path.getCanonicalPath().startsWith(stk.nextToken()))
                    return RESTRICT_WHITELIST;
            }
            return !RESTRICT_WHITELIST;
		}
		else return true;
	}

	//---------------------------------------------------------------------------------------------------------------

	%>
<%
		request.setAttribute("dir", request.getParameter("dir"));
		final String browser_name = request.getRequestURI();
		final String FOL_IMG = "";
		boolean nohtml = false;
		boolean dir_view = true;
		if (request.getParameter("Javascript") != null) {
			dir_view = false;
			nohtml = true;
			response.setHeader("Cache-Control", "public");
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
			response.setHeader("Expires", sdf.format(new Date(now.getTime() + 1000 * 60 * 60 * 24*2)));
			response.setHeader("Content-Type", "text/javascript");
			%>
			<%// This section contains the Javascript used for interface elements %>
			var check = false;
			<%// Disables the checkbox feature %>
			function dis(){check = true;}

			var DOM = 0, MS = 0, OP = 0, b = 0;
			<%// Determine the browser type %>
			function CheckBrowser(){
				if (b == 0){
					if (window.opera) OP = 1;
					if(document.getElementById) DOM = 1;
					if(document.all && !OP) MS = 1;
					b = 1;
				}
			}
			<%// Allows the whole row to be selected %>
			function selrow (element, i){
				var erst;
				CheckBrowser();
				if ((OP==1)||(MS==1)) erst = element.firstChild.firstChild;
				else if (DOM==1) erst = element.firstChild.nextSibling.firstChild;
				<%// MouseIn %>
				if (i==0){
					if (erst.checked == true) element.className='mousechecked';
					else element.className='mousein';
				}
				<%// MouseOut %>
				else if (i==1){
					if (erst.checked == true) element.className='checked';
					else element.className='mouseout';
				}
				<%    // MouseClick %>
				else if ((i==2)&&(!check)){
					if (erst.checked==true) element.className='mousein';
					else element.className='mousechecked';
					erst.click();
				}
				else check=false;
			}
			<%// Filter files and dirs in FileList%>
			function filter (begriff){
				var suche = begriff.value.toLowerCase();
				var table = document.getElementById("filetable");
				var ele;
				for (var r = 1; r < table.rows.length; r++){
					ele = table.rows[r].cells[1].innerHTML.replace(/<[^>]+>/g,"");
					if (ele.toLowerCase().indexOf(suche)>=0 )
						table.rows[r].style.display = '';
					else table.rows[r].style.display = 'none';
		      	}
			}
			<%//(De)select all checkboxes%>	
			function AllFiles(){
				for(var x=0;x < document.FileList.elements.length;x++){
					var y = document.FileList.elements[x];
					var ytr = y.parentNode.parentNode;
					var check = document.FileList.selall.checked;
					if(y.name == 'selfile' && ytr.style.display != 'none'){
						if (y.disabled != true){
							y.checked = check;
							if (y.checked == true) ytr.className = 'checked';
							else ytr.className = 'mouseout';
						}
					}
				}
			}
			
			function shortKeyHandler(_event){
				if (!_event) _event = window.event;
				if (_event.which) {
					keycode = _event.which;
				} else if (_event.keyCode) {
					keycode = _event.keyCode;
				}
				var t = document.getElementById("text_Dir");
				if (keycode == 122){
					document.getElementById("but_Zip").click();
				}
				else if (keycode == 113 || keycode == 114){
					var path = prompt("Please enter new filename", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_Ren").click();
				}
				else if (keycode == 99){
					var path = prompt("Please enter filename", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_NFi").click();
				}
				else if (keycode == 100){
					var path = prompt("Please enter directory name", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_NDi").click();
				}
				else if (keycode == 109){
					var path = prompt("Please enter move destination", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_Mov").click();
				}
				else if (keycode == 121){
					var path = prompt("Please enter copy destination", "");
					if (path == null) return;
					t.value = path;
					document.getElementById("but_Cop").click();
				}
				else if (keycode == 108){
					document.getElementById("but_Lau").click();
				}
				else if (keycode == 46){
					document.getElementById("but_Del").click();
				}
			}

			function popUp(URL){
				fname = document.getElementsByName("myFile")[0].value;
				if (fname != "")
					window.open(URL+"?first&uplMonitor="+encodeURIComponent(fname),"","width=400,height=150,resizable=yes,depend=yes")
			}
			
			<% //Functions for tailling %>
			function pageload() {
				toBottom();
				var sec = document.getElementById('seconds').value;
				timedRefresh(sec * 1000);
				
			}

			function toBottom() {
				var bChecked = document.getElementById('gotobottom').checked;
				if (bChecked == true) {
					window.scrollTo(0, document.body.scrollHeight);
				} else {
					var prevScroll = document.getElementById('prevScroll').value;
					if (prevScroll != null) {
						window.scrollTo(0, prevScroll);
					}
				}
			}
			
			function timedRefresh(timeoutPeriod) {
				window.setTimeout("pagerefresh();",timeoutPeriod);
			}
			
			function pagerefresh() {
				document.getElementById('headerform').submit();
			}

			function getUrlVars()
			{
			    var vars = [], hash;
			    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			    for(var i = 0; i < hashes.length; i++)
			    {
			        hash = hashes[i].split('=');
			        vars.push(hash[0]);
			        vars[hash[0]] = hash[1];
			    }
			    return vars;
			}
			
			function currScrollPos() {
				//var obj = document.getElementById('content');
				document.getElementById('currScroll').value = window.pageYOffset;
				document.getElementById('maxScroll').value = document.body.scrollHeight;
				var curr = window.innerHeight + window.pageYOffset;
				if (curr >= document.getElementById('maxScroll').value) {
					document.getElementById('gotobottom').checked = true;
				} else {
					document.getElementById('gotobottom').checked = false;
				}
			}
			
			document.onkeypress = shortKeyHandler;
<% 		}
		else if (request.getParameter("file") != null) {
            File f = new File(request.getParameter("file"));
            if (!isAllowed(f, false)) {
                request.setAttribute("dir", f.getParent());
                request.setAttribute("error", "You are not allowed to access "+f.getAbsolutePath());
            }
            else if (f.exists() && f.canRead()) {
                if (isPacked(f.getName(), false)) {
                }
                else{
                    String mimeType = getMimeType(f.getName());
                    response.setContentType(mimeType);
                    if (mimeType.equals("text/plain")) response.setHeader(
                            "Content-Disposition", "inline;filename=\"temp.txt\"");
                    else response.setHeader("Content-Disposition", "inline;filename=\""
                            + f.getName() + "\"");
                    BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
                    byte buffer[] = new byte[8 * 1024];
                    out.clearBuffer();
                    OutputStream out_s = new Writer2Stream(out);
                    copyStreamsWithoutClose(fileInput, out_s, buffer);
                    fileInput.close();
                    out_s.flush();
                    nohtml = true;
                    dir_view = false;
                }
            }
            else {
                request.setAttribute("dir", f.getParent());
                request.setAttribute("error", "File " + f.getAbsolutePath()
                        + " does not exist or is not readable on the server");
            }
		}
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(SAVE_AS_ZIP))) {
			Vector v = expandFileList(request.getParameterValues("selfile"), false);
			String notAllowedFile = null;
			for (int i = 0;i < v.size(); i++){
				File f = (File) v.get(i);
				if (!isAllowed(f, false)){
					notAllowedFile = f.getAbsolutePath();
					break;
				}
			}
			if (notAllowedFile != null){
				request.setAttribute("error", "You are not allowed to access " + notAllowedFile);
			}
			else if (v.size() == 0) {
				request.setAttribute("error", "No files selected");
			}
			else {
				File dir_file = new File("" + request.getAttribute("dir"));
				int dir_l = dir_file.getAbsolutePath().length();
				response.setContentType("application/zip");
				response.setHeader("Content-Disposition", "attachment;filename=\"rename_me.zip\"");
				out.clearBuffer();
				ZipOutputStream zipout = new ZipOutputStream(new Writer2Stream(out));
				zipout.setComment("Created by PC ");
				zipout.setLevel(COMPRESSION_LEVEL);
				for (int i = 0; i < v.size(); i++) {
					File f = (File) v.get(i);
					if (f.canRead()) {
						zipout.putNextEntry(new ZipEntry(f.getAbsolutePath().substring(dir_l + 1)));
						BufferedInputStream fr = new BufferedInputStream(new FileInputStream(f));
						byte buffer[] = new byte[0xffff];
						copyStreamsWithoutClose(fr, zipout, buffer);
						fr.close();
						zipout.closeEntry();
					}
				}
				zipout.finish();
				out.flush();
				nohtml = true;
				dir_view = false;
			}
		}
		else if (request.getParameter("downfile") != null) {
			String filePath = request.getParameter("downfile");
			File f = new File(filePath);
			if (!isAllowed(f, false)){
				request.setAttribute("dir", f.getParent());
				request.setAttribute("error", "You are not allowed to access " + f.getAbsoluteFile());
			}
			else if (f.exists() && f.canRead()) {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + f.getName()
						+ "\"");
				response.setContentLength((int) f.length());
				BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
				byte buffer[] = new byte[8 * 1024];
				out.clearBuffer();
				OutputStream out_s = new Writer2Stream(out);
				copyStreamsWithoutClose(fileInput, out_s, buffer);
				fileInput.close();
				out_s.flush();
				nohtml = true;
				dir_view = false;
			}
			else {
				request.setAttribute("dir", f.getParent());
				request.setAttribute("error", "File " + f.getAbsolutePath()
						+ " does not exist or is not readable on the server");
			}
		}
		if (nohtml) return;
			if (request.getAttribute("dir") == null) {
				String path = null;
				if (application.getRealPath(request.getRequestURI()) != null) {
					File f = new File(application.getRealPath(request.getRequestURI())).getParentFile();
					//This is a hack needed for tomcat
					while (f != null && !f.exists())
						f = f.getParentFile();
					if (f != null)
						path = f.getAbsolutePath();
				}
				if (path == null) { 
					path = new File(".").getAbsolutePath();
				}
                if (!isAllowed(new File(path), false)){
                    if (RESTRICT_PATH.indexOf(";")<0) path = RESTRICT_PATH;
                    else path = RESTRICT_PATH.substring(0, RESTRICT_PATH.indexOf(";"));
                }
				request.setAttribute("dir", path);
			}%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<meta name="robots" content="noindex">
<meta http-equiv="expires" content="0">
<meta http-equiv="pragma" content="no-cache">
      <% if (request.getParameter("uplMonitor") == null) {%>
	<style type="text/css">
		input.button {background-color: #c0c0c0; color: #666666;
		border: 1px solid #999999; margin: 5px 1px 5px 1px;}
		input.textfield {margin: 5px 1px 5px 1px;}
		input.button:Hover { color: #444444 }
		table.filelist {background-color:#666666; width:100%; border:0px none #ffffff}
		.formular {margin: 1px; background-color:#ffffff; padding: 1em; border:1px solid #000000;}
		.formular2 {margin: 1px;}
		th { background-color:#c0c0c0 }
		tr.mouseout { background-color:#ffffff; }
		tr.mousein  { background-color:#eeeeee; }
		tr.checked  { background-color:#cccccc }
		tr.mousechecked { background-color:#c0c0c0 }
		td { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #666666;}
		td.message { background-color: #FFFF00; color: #000000; text-align:center; font-weight:bold}
		td.error { background-color: #FF0000; color: #000000; text-align:center; font-weight:bold}
		A { text-decoration: none; }
		A:Hover { color : Red; text-decoration : underline; }
		BODY { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #666666;}
	</style>
	<%}
		
        if (!isAllowed(new File((String)request.getAttribute("dir")), false)){
            request.setAttribute("error", "You are not allowed to access " + request.getAttribute("dir"));
        }
		else if (request.getParameter("uplMonitor") != null) {%>
	<style type="text/css">
		BODY { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #666666;}
	</style><%
			String fname = request.getParameter("uplMonitor");
			boolean first = false;
			if (request.getParameter("first") != null) first = true;
			UplInfo info = new UplInfo();
			if (!first) {
				info = UploadMonitor.getInfo(fname);
				if (info == null) {
					int posi = fname.lastIndexOf("\\");
					if (posi != -1) info = UploadMonitor.getInfo(fname.substring(posi + 1));
				}
				if (info == null) {
					int posi = fname.lastIndexOf("/");
					if (posi != -1) info = UploadMonitor.getInfo(fname.substring(posi + 1));
				}
			}
			dir_view = false;
			request.setAttribute("dir", null);
			if (info.aborted) {
				UploadMonitor.remove(fname);
				%>
</head>
<body>
<b>Upload of <%=fname%></b><br><br>
Upload aborted.</body>
</html><%
			}
			else if (info.totalSize != info.currSize || info.currSize == 0) {
				%>
<META HTTP-EQUIV="Refresh" CONTENT="<%=UPLOAD_MONITOR_REFRESH%>;URL=<%=browser_name %>?uplMonitor=<%=URLEncoder.encode(fname)%>">
</head>
<body>
<b>Upload of <%=fname%></b><br><br>
<center>
<table height="20px" width="90%" bgcolor="#eeeeee" style="border:1px solid #cccccc"><tr>
<td bgcolor="blue" width="<%=info.getPercent()%>%"></td><td width="<%=100-info.getPercent()%>%"></td>
</tr></table></center>
<%=convertFileSize(info.currSize)%> from <%=convertFileSize(info.totalSize)%>
(<%=info.getPercent()%> %) uploaded (Speed: <%=info.getUprate()%>).<br>
Time: <%=info.getTimeElapsed()%> from <%=info.getTimeEstimated()%>
</body>
</html><%
			}
			else {
				UploadMonitor.remove(fname);
				%>
</head>
<body onload="javascript:window.close()">
<b>Upload of <%=fname%></b><br><br>
Upload finished.
</body>
</html>






<%
			}
		}
		else if (request.getParameter("command") != null) {
            if (!NATIVE_COMMANDS){
                request.setAttribute("error", "Execution of native commands is not allowed!");
            }
			else if (!"Cancel".equalsIgnoreCase(request.getParameter("Submit"))) {
%>
<title>Launch commands in <%=request.getAttribute("dir")%></title>
</head>
<body><center>
<h2><%=LAUNCH_COMMAND %></h2><br />
<%
				out.println("<form action=\"" + browser_name + "\" method=\"Post\">\n"
						+ "<textarea name=\"text\" wrap=\"off\" rows=\"" + EDITFIELD_ROWS
						+ "\" style=\"width:100%;\" readonly>");
				String ret = "";
				if (!request.getParameter("command").equalsIgnoreCase(""))
                    ret = startProcess(
						request.getParameter("command"), (String) request.getAttribute("dir"), request.getParameter("commandInterpreter"));
				out.println(ret);
				boolean dos = true;
				try {
					dos = request.getParameter("commandInterpreter").equals("dos");
				} catch (Exception e){
				}
%></textarea>
	<input type="hidden" name="dir" value="<%= request.getAttribute("dir")%>">
	<br /><br />
	<table class="formular" style="width:70%;">
	<tr><td title="Enter your command">
	Command: <input style="width:80%;" type="text" name="command" value="">
	</td></tr>
	<tr><td>
	<input type="radio" name="commandInterpreter" value="dos" <%= dos?"checked":""%>>Ms-Dos/Windows
	<input type="radio" name="commandInterpreter" value="unix" <%= dos?"":"checked"%>>Unix
	<input class="button" type="Submit" name="Submit" value="Launch">
	<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
	<input type="Submit" class="button" name="Submit" value="Cancel"></td></tr>
	</table>
	</form>
	<br />
	<hr>
	</center>
</body>
</html>
<%
				dir_view = false;
				request.setAttribute("dir", null);
			}
		}

		//Aqui come?a o SQLConsole
		else if (request.getParameter("sqlConsole") != null) {
            if (!SQL_CONSOLE){
                request.setAttribute("error", "SQL Console is not allowed!");
            }
			else if (!"Cancel".equalsIgnoreCase(request.getParameter("Submit"))) {
%>
<title>SQL Console</title>
</head>
<body><center>
	<h2>SQL Console</h2>
	<% out.println("<form action=\"" + browser_name + "\" method=\"Post\">\n"); %>
	<input type="hidden" name="dir" value="<%= request.getAttribute("dir")%>">
	<table class="formular" style="width:100%;">
	<tr><td>
	 <% EasySQL easy = new EasySQL();
	 
	    String d0 = request.getParameter("d0");
	    if (d0 == null) d0= "";       
	    String d1 = request.getParameter("d1");
	    if (d1 == null) d1= "";       
	    String d2 = request.getParameter("d2"); 
	    if (d2 == null) d2= "";
	    String d3 = request.getParameter("d3");
	    if (d3 == null) d3= "";       
	    String d4 = request.getParameter("d4");
	    if (d4 == null) d4= "";
	    String maxRows = request.getParameter("maxRows");
	    if (maxRows == null) maxRows= "0";
	    String qry = request.getParameter("qry");
	    if (qry == null) qry= "";  %>  
<% if (!easy.openConnection(d0, d1, d2, d3, d4)) { %>	
Not Connected to Database
  <% } else if(!qry.equals("")) { 
  		String [] headings = easy.openTable(qry, maxRows);
   		%> 	<table class="filelist" cellspacing="1px" cellpadding="0px">
			<% for (int x = 0; x < headings.length; x++) {%>			
				<th>&nbsp;<%=headings[x]%>&nbsp;</th>
 	   		<% } %> </tr> <%  
		while (easy.getMoreColumns(qry)) {
			String[] r = easy.getColumns();
			%> <tr class="mouseout" onmouseover="this.className='mousein'" onmouseout="this.className='mouseout'"><%
			for (int x = 0; x < r.length; x++) { %> 
			 <td nowrap="nowrap"><%= r[x] %></td>
	    	<%}%>
	    </tr>
	    <%}%>
	    </table>
	  </td></tr><tr><td align="left">Selected Rows: <%= easy.getRowCount() %>
<% easy.closeConnection(); %>
<% } %>
	</td></tr></table>
	<br />
	<table class="formular" style="width:100%;">
      <tr><td align="right">Datasource's JNDI:</td><td align="left"><input value="<%= d0 %>" size="80" name="d0"></td><td></td></tr>
      <tr><td align="right">Driver:</td><td align="left"><input value="<%= d1 %>" size="80" name="d1"></td><td></td></tr>
      <tr><td align="right">URL:</td><td align="left"><input value="<%= d2 %>" size="80" name="d2"></td><td></td></tr>
      <tr><td align="right">User:</td><td align="left"><input value="<%= d3 %>" size="80" name="d3"></td><td></td></tr>
      <tr><td align="right">Password:</td><td align="left"><input value="<%= d4 %>" size="80" name="d4"></td><td></td></tr>
      <tr><td align="right" style="vertical-align: top;">SQL Statement:</td><td align="left" colspan=2><textarea rows="15" name="qry" style="width:100%;" ><%= qry %></textarea></td></tr>
      <tr><td align="right">Max Rows:</td><td align="left"><input value="<%= maxRows %>" size="10" name="maxRows"></td><td></td></tr>


	<tr><td colspan="3" ><input class="button" type="Submit" name="Submit" value="Launch">
	<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
	<input type="hidden" name="sqlConsole" value="">
	<input type="Submit" class="button" name="Submit" value="Cancel"></td></tr>
	</table>
	</form>
	<br/>
	<hr>
</center>
</body>
</html>
<%
				dir_view = false;
				request.setAttribute("dir", null);
			}
		}

		//Aqui comeca o Tailling
		else if (request.getParameter("tailfile") != null) {
			if (!"Cancel".equalsIgnoreCase(request.getParameter("Submit"))) {
%>
<script type="text/javascript" src="<%=browser_name %>?Javascript">
</script>
<title>Tailling File: <%=request.getParameter("tailfile")%></title>
</head>
<body onscroll="javascript:currScrollPos();" onload="javascript:pageload();">
    <% 
   String strLineSep = System.getProperty("line.separator");
   if (strLineSep != null) {
      if(strLineSep.equals("\r\n")) {
          strLineSep = "\n";
      }
   }
   String strFileSep = System.getProperty("file.separator");
         
   int iLines = 50;
   String strReqLines = request.getParameter("lines");
   if (strReqLines != null) {
       iLines = Integer.parseInt(strReqLines);
   }
   String strChecked = "";
   String strCheckbox = request.getParameter("gotobottom");
   if (strCheckbox != null) {
	   strChecked = "checked";   
   } else {
	   if (request.getParameter("prevScroll") == null) {
		   strChecked = "checked"; 
	   }
   }
   
   String strPrevScroll = request.getParameter("currScroll");
   if (strPrevScroll == null) {
        strPrevScroll = "0";
   }
   
   String strSeconds = request.getParameter("seconds");
   if (strSeconds == null) {
	   strSeconds = "5";
   }
   
   String tailFile = request.getParameter("tailfile");
   File f = new File(tailFile);
   LogFile logFile = new LogFile(f);
   Tail t = new Tail(logFile);
   String strTaillog = "";
   try {
       strTaillog = t.tailLog(iLines);
       strTaillog = strTaillog.replaceAll(strLineSep, "<br>");
   }catch(Exception e1) {
       
   }
%>
    <table style="width: 100%; margin-top:0px;">
    <th style="position:fixed; font-weight: normal; width: 100%; padding:0px; top:0px; left: 0px;">
        <form id="headerform" action="<%=browser_name%>" method="post" name="taillog">
            Tailling File: <%=request.getParameter("tailfile")%> | Number of lines: <input type="text" name="lines" value="<%=iLines %>" size="4" maxlength="5" onchange="this.form.submit();" />
            | Goto bottom: <input type="checkbox" id="gotobottom" name="gotobottom" value="gotobottom" <%=strChecked %> onchange="javascript:pagerefresh()" /> |
            <input type="hidden" id="currScroll" name="currScroll" value="" />
            <input type="hidden" id="prevScroll" name="prevScroll" value="<%=strPrevScroll %>" />
            <input type="hidden" id="maxScroll" name="maxScroll" value="" />
            <input type="hidden" id="tailfile" name="tailfile" value="<%=tailFile %>" />
            <a href="javascript:pagerefresh()">Refresh</a>&nbsp;every: <input type="text" id="seconds" name="seconds" value="<%=strSeconds %>" size="2" onchange="this.form.submit();" /> seconds
        </form>
    </th>    	 
	<tr>
		<td><p class="content" id="content"><%=strTaillog %></p></td>
	</tr>
    </table>
</body>
</html>
<%
				dir_view = false;
				request.setAttribute("dir", null);
			}
%>
<%		
		}
		else if (request.getParameter("file") != null) {
			File f = new File(request.getParameter("file"));
            if (!isAllowed(f, false)){
                request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
            }
			else if (isPacked(f.getName(), false)) {
				try {
					ZipFile zf = new ZipFile(f);
					Enumeration entries = zf.entries();
%>
<title><%= f.getAbsolutePath()%></title>
</head>
<body>
	<h2>Content of <%=conv2Html(f.getName())%></h2><br />
	<table class="filelist" cellspacing="1px" cellpadding="0px">
	<th>Name</th><th>Uncompressed size</th><th>Compressed size</th><th>Compr. ratio</th><th>Date</th>
<%
					long size = 0;
					int fileCount = 0;
					while (entries.hasMoreElements()) {
						ZipEntry entry = (ZipEntry) entries.nextElement();
						if (!entry.isDirectory()) {
							fileCount++;
							size += entry.getSize();
							long ratio = 0;
							if (entry.getSize() != 0) ratio = (entry.getCompressedSize() * 100)
									/ entry.getSize();
							out.println("<tr class=\"mouseout\"><td>" + conv2Html(entry.getName())
									+ "</td><td>" + convertFileSize(entry.getSize()) + "</td><td>"
									+ convertFileSize(entry.getCompressedSize()) + "</td><td>"
									+ ratio + "%" + "</td><td>"
									+ dateFormat.format(new Date(entry.getTime())) + "</td></tr>");

						}
					}
					zf.close();
					dir_view = false;
					request.setAttribute("dir", null);
%>
	</table>
	<p align=center>
	<b><%=convertFileSize(size)%> in <%=fileCount%> files in <%=f.getName()%>. Compression ratio: <%=(f.length() * 100) / size%>%
	</b></p>
</body></html>
<%
				}
				catch (ZipException ex) {
					request.setAttribute("error", "Cannot read " + f.getName()
							+ ", no valid zip file");
				}
				catch (IOException ex) {
					request.setAttribute("error", "Reading of " + f.getName() + " aborted. Error: "
							+ ex);
				}
			}
		}
		else if ((request.getContentType() != null)
				&& (request.getContentType().toLowerCase().startsWith("multipart"))) {
			if (!ALLOW_UPLOAD){
                request.setAttribute("error", "Upload is forbidden!");
            }
			response.setContentType("text/html");
			HttpMultiPartParser parser = new HttpMultiPartParser();
			boolean error = false;
			try {
				int bstart = request.getContentType().lastIndexOf("oundary=");
				String bound = request.getContentType().substring(bstart + 8);
				int clength = request.getContentLength();
				Hashtable ht = parser
						.processData(request.getInputStream(), bound, tempdir, clength);
                if (!isAllowed(new File((String)ht.get("dir")), false)){
                	request.setAttribute("error", "You are not allowed to access " + ht.get("dir"));
                    error = true;
                }
				else if (ht.get("myFile") != null) {
					FileInfo fi = (FileInfo) ht.get("myFile");
					File f = fi.file;
					UplInfo info = UploadMonitor.getInfo(fi.clientFileName);
					if (info != null && info.aborted) {
						f.delete();
						request.setAttribute("error", "Upload aborted");
					}
					else {
						String path = (String) ht.get("dir");
						if (!path.endsWith(File.separator)) path = path + File.separator;
						if (!f.renameTo(new File(path + f.getName()))) {
							request.setAttribute("error", "Cannot upload file.");
							error = true;
							f.delete();
						}
					}
				}
				else {
					request.setAttribute("error", "No file selected for upload");
					error = true;
				}
				request.setAttribute("dir", (String) ht.get("dir"));
			}
			catch (Exception e) {
				request.setAttribute("error", "Error " + e + ". Upload aborted");
				error = true;
			}
			if (!error) request.setAttribute("message", "File upload correctly finished.");
		}
		else if (request.getParameter("editfile") != null) {
			File ef = new File(request.getParameter("editfile"));
            if (!isAllowed(ef, true)){
                request.setAttribute("error", "You are not allowed to access " + ef.getAbsolutePath());
            }
            else{
%>
<title>Edit <%=conv2Html(request.getParameter("editfile"))%></title>
</head>
<body>
<center>
<h2>Edit <%=conv2Html(request.getParameter("editfile"))%></h2><br />
<%
                BufferedReader reader = new BufferedReader(new FileReader(ef));
                String disable = "";
                if (!ef.canWrite()) disable = " readonly";
                out.println("<form action=\"" + browser_name + "\" method=\"Post\">\n"
                        + "<textarea name=\"text\" wrap=\"off\" rows=\"" + EDITFIELD_ROWS
                        + "\" style=\"width:100%;\" " + disable + ">");
                String c;
                int i;
                boolean dos = false;
                boolean cr = false;
                while ((i = reader.read()) >= 0) {
                    out.print(conv2Html(i));
                    if (i == '\r') cr = true;
                    else if (cr && (i == '\n')) dos = true;
                    else cr = false;
                }
                reader.close();
                request.setAttribute("dir", null);
                dir_view = false;

%></textarea><br /><br />
<table class="formular">
	<input type="hidden" name="nfile" value="<%= request.getParameter("editfile")%>">
	<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
		<tr><td colspan="2"><input type="radio" name="lineformat" value="dos" <%= dos?"checked":""%>>Ms-Dos/Windows
		<input type="radio" name="lineformat" value="unix" <%= dos?"":"checked"%>>Unix
		<input type="checkbox" name="Backup" checked>Write backup</td></tr>
		<tr><td title="Enter the new filename"><input type="text" name="new_name" value="<%=ef.getName()%>">
		<input type="Submit" name="Submit" value="Save"></td>
	</form>
	<form action="<%=browser_name%>" method="Post">
	<td align="left">
	<input type="Submit" name="Submit" value="Cancel">
	<input type="hidden" name="nfile" value="<%= request.getParameter("editfile")%>">
	<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
	</td>
	</form>
	</tr>
	</table>
	</center>
	<br />
	<hr>
</body>
</html>
<%
            }
		}
		else if (request.getParameter("nfile") != null) {
			File f = new File(request.getParameter("nfile"));
			if (request.getParameter("Submit").equals("Save")) {
				File new_f = new File(getDir(f.getParent(), request.getParameter("new_name")));
	            if (!isAllowed(new_f, true)){
	                request.setAttribute("error", "You are not allowed to access " + new_f.getAbsolutePath());
	            }
				if (new_f.exists() && new_f.canWrite() && request.getParameter("Backup") != null) {
					File bak = new File(new_f.getAbsolutePath() + ".bak");
					bak.delete();
					new_f.renameTo(bak);
				}
				if (new_f.exists() && !new_f.canWrite()) request.setAttribute("error",
						"Cannot write to " + new_f.getName() + ", file is write protected.");
				else {
					BufferedWriter outs = new BufferedWriter(new FileWriter(new_f));
					StringReader text = new StringReader(request.getParameter("text"));
					int i;
					boolean cr = false;
					String lineend = "\n";
					if (request.getParameter("lineformat").equals("dos")) lineend = "\r\n";
					while ((i = text.read()) >= 0) {
						if (i == '\r') cr = true;
						else if (i == '\n') {
							outs.write(lineend);
							cr = false;
						}
						else if (cr) {
							outs.write(lineend);
							cr = false;
						}
						else {
							outs.write(i);
							cr = false;
						}
					}
					outs.flush();
					outs.close();
				}
			}
			request.setAttribute("dir", f.getParent());
		}
		else if (request.getParameter("unpackfile") != null) {
			File f = new File(request.getParameter("unpackfile"));
			String root = f.getParent();
			request.setAttribute("dir", root);
            if (!isAllowed(new File(root), true)){
                request.setAttribute("error", "You are not allowed to access " + root);
            }
			else if (!f.exists()) {
				request.setAttribute("error", "Cannot unpack " + f.getName()
						+ ", file does not exist");
			}
			else if (!f.getParentFile().canWrite()) {
				request.setAttribute("error", "Cannot unpack " + f.getName()
						+ ", directory is write protected.");
			}
			else if (f.getName().toLowerCase().endsWith(".gz")) {
				String newName = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 3);
				try {
					byte buffer[] = new byte[0xffff];
					copyStreams(new GZIPInputStream(new FileInputStream(f)), new FileOutputStream(
							newName), buffer);
				}
				catch (IOException ex) {
					request.setAttribute("error", "Unpacking of " + f.getName()
							+ " aborted. Error: " + ex);
				}
			}
			else {
				try {
					ZipFile zf = new ZipFile(f);
					Enumeration entries = zf.entries();
					boolean error = false;
					while (entries.hasMoreElements()) {
						ZipEntry entry = (ZipEntry) entries.nextElement();
						if (!entry.isDirectory()
								&& new File(root + File.separator + entry.getName()).exists()) {
							request.setAttribute("error", "Cannot unpack " + f.getName()
									+ ", File " + entry.getName() + " already exists.");
							error = true;
							break;
						}
					}
					if (!error) {
						entries = zf.entries();
						byte buffer[] = new byte[0xffff];
						while (entries.hasMoreElements()) {
							ZipEntry entry = (ZipEntry) entries.nextElement();
							File n = new File(root + File.separator + entry.getName());
							if (entry.isDirectory()) n.mkdirs();
							else {
								n.getParentFile().mkdirs();
								n.createNewFile();
								copyStreams(zf.getInputStream(entry), new FileOutputStream(n),
										buffer);
							}
						}
						zf.close();
						request.setAttribute("message", "Unpack of " + f.getName()
								+ " was successful.");
					}
				}
				catch (ZipException ex) {
					request.setAttribute("error", "Cannot unpack " + f.getName()
							+ ", no valid zip file");
				}
				catch (IOException ex) {
					request.setAttribute("error", "Unpacking of " + f.getName()
							+ " aborted. Error: " + ex);
				}
			}
		}
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(DELETE_FILES))) {
			Vector v = expandFileList(request.getParameterValues("selfile"), true);
			boolean error = false;
			for (int i = v.size() - 1; i >= 0; i--) {
				File f = (File) v.get(i);
                if (!isAllowed(f, true)){
                    request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
                    error = true;
                    break;
                }
				if (!f.canWrite() || !f.delete()) {
					request.setAttribute("error", "Cannot delete " + f.getAbsolutePath()
							+ ". Deletion aborted");
					error = true;
					break;
				}
			}
			if ((!error) && (v.size() > 1)) request.setAttribute("message", "All files deleted");
			else if ((!error) && (v.size() > 0)) request.setAttribute("message", "File deleted");
			else if (!error) request.setAttribute("error", "No files selected");
		}
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(CREATE_DIR))) {
			String dir = "" + request.getAttribute("dir");
			String dir_name = request.getParameter("cr_dir");
			String new_dir = getDir(dir, dir_name);
            if (!isAllowed(new File(new_dir), true)){
                request.setAttribute("error", "You are not allowed to access " + new_dir);
            }
			else if (new File(new_dir).mkdirs()) {
				request.setAttribute("message", "Directory created");
			}
			else request.setAttribute("error", "Creation of directory " + new_dir + " failed");
		}
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(CREATE_FILE))) {
			String dir = "" + request.getAttribute("dir");
			String file_name = request.getParameter("cr_dir");
			String new_file = getDir(dir, file_name);
            if (!isAllowed(new File(new_file), true)){
                request.setAttribute("error", "You are not allowed to access " + new_file);
            }
			else if (!"".equals(file_name.trim()) && !file_name.endsWith(File.separator)) {
				if (new File(new_file).createNewFile()) request.setAttribute("message",
						"File created");
				else request.setAttribute("error", "Creation of file " + new_file + " failed");
			}
			else request.setAttribute("error", "Error: " + file_name + " is not a valid filename");
		}
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(RENAME_FILE))) {
			Vector v = expandFileList(request.getParameterValues("selfile"), true);
			String dir = "" + request.getAttribute("dir");
			String new_file_name = request.getParameter("cr_dir");
			String new_file = getDir(dir, new_file_name);
            if (!isAllowed(new File(new_file), true)){
                request.setAttribute("error", "You are not allowed to access " + new_file);
            }
			else if (v.size() <= 0) request.setAttribute("error",
					"Select exactly one file or folder. Rename failed");
			else if ((v.size() > 1) && !(((File) v.get(0)).isDirectory())) request.setAttribute(
					"error", "Select exactly one file or folder. Rename failed");
			else if ((v.size() > 1) && ((File) v.get(0)).isDirectory()
					&& !(((File) v.get(0)).getPath().equals(((File) v.get(1)).getParent()))) {
				request.setAttribute("error", "Select exactly one file or folder. Rename failed");
			}
			else {
				File f = (File) v.get(0);
                if (!isAllowed(f, true)){
                    request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
                }
				else if ((new_file.trim() != "") && !new_file.endsWith(File.separator)) {
					if (!f.canWrite() || !f.renameTo(new File(new_file.trim()))) {
						request.setAttribute("error", "Creation of file " + new_file + " failed");
					}
					else request.setAttribute("message", "Renamed file "
							+ ((File) v.get(0)).getName() + " to " + new_file);
				}
				else request.setAttribute("error", "Error: \"" + new_file_name
						+ "\" is not a valid filename");
			}
		}
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(MOVE_FILES))) {
			Vector v = expandFileList(request.getParameterValues("selfile"), true);
			String dir = "" + request.getAttribute("dir");
			String dir_name = request.getParameter("cr_dir");
			String new_dir = getDir(dir, dir_name);
            if (!isAllowed(new File(new_dir), false)){
                request.setAttribute("error", "You are not allowed to access " + new_dir);
            }
            else{
    			boolean error = false;
                if (!new_dir.endsWith(File.separator)) new_dir += File.separator;
                for (int i = v.size() - 1; i >= 0; i--) {
                    File f = (File) v.get(i);
                    if (!isAllowed(f, true)){
                        request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
                        error = true;
                        break;
                    }
                    else if (!f.canWrite() || !f.renameTo(new File(new_dir
                            + f.getAbsolutePath().substring(dir.length())))) {
                        request.setAttribute("error", "Cannot move " + f.getAbsolutePath()
                                + ". Move aborted");
                        error = true;
                        break;
                    }
                }
                if ((!error) && (v.size() > 1)) request.setAttribute("message", "All files moved");
                else if ((!error) && (v.size() > 0)) request.setAttribute("message", "File moved");
                else if (!error) request.setAttribute("error", "No files selected");
            }
		}
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(COPY_FILES))) {
			Vector v = expandFileList(request.getParameterValues("selfile"), true);
			String dir = (String) request.getAttribute("dir");
			if (!dir.endsWith(File.separator)) dir += File.separator;
			String dir_name = request.getParameter("cr_dir");
			String new_dir = getDir(dir, dir_name);
            if (!isAllowed(new File(new_dir), true)){
                request.setAttribute("error", "You are not allowed to access " + new_dir);
            }
            else{
    			boolean error = false;
                if (!new_dir.endsWith(File.separator)) new_dir += File.separator;
                try {
                    byte buffer[] = new byte[0xffff];
                    for (int i = 0; i < v.size(); i++) {
                        File f_old = (File) v.get(i);
                        File f_new = new File(new_dir + f_old.getAbsolutePath().substring(dir.length()));
                        if (!isAllowed(f_old, false)|| !isAllowed(f_new, true)){
                            request.setAttribute("error", "You are not allowed to access " + f_new.getAbsolutePath());
                            error = true;
                        }
                        else if (f_old.isDirectory()) f_new.mkdirs();
                        else if (!f_new.exists()) {
                            copyStreams(new FileInputStream(f_old), new FileOutputStream(f_new), buffer);
                        }
                        else {
                            request.setAttribute("error", "Cannot copy " + f_old.getAbsolutePath()
                                    + ", file already exists. Copying aborted");
                            error = true;
                            break;
                        }
                    }
                }
                catch (IOException e) {
                    request.setAttribute("error", "Error " + e + ". Copying aborted");
                    error = true;
                }
                if ((!error) && (v.size() > 1)) request.setAttribute("message", "All files copied");
                else if ((!error) && (v.size() > 0)) request.setAttribute("message", "File copied");
                else if (!error) request.setAttribute("error", "No files selected");
            }
		}
		if (dir_view && request.getAttribute("dir") != null) {
			File f = new File("" + request.getAttribute("dir"));
			if (!f.exists() || !isAllowed(f, false)) {
				if (!f.exists()){
                    request.setAttribute("error", "Directory " + f.getAbsolutePath() + " does not exist.");
                }
                else{
                    request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
                }
				if (request.getAttribute("olddir") != null && isAllowed(new File((String) request.getAttribute("olddir")), false)) {
					f = new File("" + request.getAttribute("olddir"));
				}
				else {
					if (f.getParent() != null && isAllowed(f, false)) f = new File(f.getParent());
				}
				if (!f.exists()) {
					String path = null;
					if (application.getRealPath(request.getRequestURI()) != null) path = new File(
							application.getRealPath(request.getRequestURI())).getParent();

					if (path == null)
					path = new File(".").getAbsolutePath();
					f = new File(path);
				}
				if (isAllowed(f, false)) request.setAttribute("dir", f.getAbsolutePath());
                else request.setAttribute("dir", null);
			}
%>
<script type="text/javascript" src="<%=browser_name %>?Javascript">
</script>
<title><%=request.getAttribute("dir")%></title>
</head>
<body>
<%
			if (request.getAttribute("message") != null) {
				out.println("<table border=\"0\" width=\"100%\"><tr><td class=\"message\">");
				out.println(request.getAttribute("message"));
				out.println("</td></tr></table>");
			}
			if (request.getAttribute("error") != null) {
				out.println("<table border=\"0\" width=\"100%\"><tr><td class=\"error\">");
				out.println(request.getAttribute("error"));
				out.println("</td></tr></table>");
			}
			if (request.getParameter("unlock") != null ) request.getSession(true).setAttribute("unlock", "pc");
            if (request.getAttribute("dir") != null && request.getSession(true).getAttribute("unlock") != null){
%>

	<form class="formular" action="<%= browser_name %>" method="Post" name="FileList">
    Filename filter: <input name="filt" onKeypress="event.cancelBubble=true;" onkeyup="filter(this)" type="text">
    <br /><br />
	<table id="filetable" class="filelist" cellspacing="1px" cellpadding="0px">
<%
			String dir = URLEncoder.encode("" + request.getAttribute("dir"));
			String cmd = browser_name + "?dir=" + dir;
			int sortMode = 1;
			if (request.getParameter("sort") != null) sortMode = Integer.parseInt(request
					.getParameter("sort"));
			int[] sort = new int[] {1, 2, 3, 4};
			for (int i = 0; i < sort.length; i++)
				if (sort[i] == sortMode) sort[i] = -sort[i];
			out.print("<tr><th>&nbsp;</th><th title=\"Sort files by name\" align=left><a href=\""
					+ cmd + "&amp;sort=" + sort[0] + "\">Name</a></th>"
					+ "<th title=\"Sort files by size\" align=\"right\"><a href=\"" + cmd
					+ "&amp;sort=" + sort[1] + "\">Size</a></th>"
					+ "<th title=\"Sort files by type\" align=\"center\"><a href=\"" + cmd
					+ "&amp;sort=" + sort[3] + "\">Type</a></th>"
					+ "<th title=\"Sort files by date\" align=\"left\"><a href=\"" + cmd
					+ "&amp;sort=" + sort[2] + "\">Date</a></th>"
					+ "<th>&nbsp;</th>");
			if (!READ_ONLY) out.print ("<th>&nbsp;</th>");
			out.print ("<th>&nbsp;</th>");
			out.println("</tr>");
			char trenner = File.separatorChar;
			File[] entry = File.listRoots();
			for (int i = 0; i < entry.length; i++) {
				boolean forbidden = false;
				for (int i2 = 0; i2 < FORBIDDEN_DRIVES.length; i2++) {
					if (entry[i].getAbsolutePath().toLowerCase().equals(FORBIDDEN_DRIVES[i2])) forbidden = true;
				}
				if (!forbidden) {
					out.println("<tr class=\"mouseout\" onmouseover=\"this.className='mousein'\""
							+ "onmouseout=\"this.className='mouseout'\">");
					out.println("<td>&nbsp;</td><td align=left >");
					String name = URLEncoder.encode(entry[i].getAbsolutePath());
					String buf = entry[i].getAbsolutePath();
					out.println(" &nbsp;<a href=\"" + browser_name + "?sort=" + sortMode
							+ "&amp;dir=" + name + "\">[" + buf + "]</a>");
					out.print("</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td></td><td></td></tr>");
				}
			}
			if (f.getParent() != null) {
				out.println("<tr class=\"mouseout\" onmouseover=\"this.className='mousein'\""
						+ "onmouseout=\"this.className='mouseout'\">");
				out.println("<td></td><td align=left>");
				out.println(" &nbsp;<a href=\"" + browser_name + "?sort=" + sortMode + "&amp;dir="
						+ URLEncoder.encode(f.getParent()) + "\">" + FOL_IMG + "[..]</a>");
				out.print("</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td></td><td></td></tr>");
			}
			entry = f.listFiles();
			if (entry == null) entry = new File[] {};
			long totalSize = 0; 
			long fileCount = 0; 
			if (entry != null && entry.length > 0) {
				Arrays.sort(entry, new FileComp(sortMode));
				for (int i = 0; i < entry.length; i++) {
					String name = URLEncoder.encode(entry[i].getAbsolutePath());
					String type = "File"; // This String will tell the extension of the file
					if (entry[i].isDirectory()) type = "DIR"; // It's a DIR
					else {
						String tempName = entry[i].getName().replace(' ', '_');
						if (tempName.lastIndexOf('.') != -1) type = tempName.substring(
								tempName.lastIndexOf('.')).toLowerCase();
					}
					String ahref = "<a onmousedown=\"dis()\" href=\"" + browser_name + "?sort="
							+ sortMode + "&amp;";
					String dlink = "&nbsp;"; 
					String elink = "&nbsp;";
					String tlink = "&nbsp;";
					String buf = conv2Html(entry[i].getName());
					if (!entry[i].canWrite()) buf = "<i>" + buf + "</i>";
					String link = buf; 
					if (entry[i].isDirectory()) {
						if (entry[i].canRead() && USE_DIR_PREVIEW) {
							File[] fs = entry[i].listFiles();
							if (fs == null) fs = new File[] {};
							Arrays.sort(fs, new FileComp());
							StringBuffer filenames = new StringBuffer();
							for (int i2 = 0; (i2 < fs.length) && (i2 < 10); i2++) {
								String fname = conv2Html(fs[i2].getName());
								if (fs[i2].isDirectory()) filenames.append("[" + fname + "];");
								else filenames.append(fname + ";");
							}
							if (fs.length > DIR_PREVIEW_NUMBER) filenames.append("...");
							else if (filenames.length() > 0) filenames
									.setLength(filenames.length() - 1);
							link = ahref + "dir=" + name + "\" title=\"" + filenames + "\">"
									+ FOL_IMG + "[" + buf + "]</a>";
						}
						else if (entry[i].canRead()) {
							link = ahref + "dir=" + name + "\">" + FOL_IMG + "[" + buf + "]</a>";
						}
						else link = FOL_IMG + "[" + buf + "]";
					}
					else if (entry[i].isFile()) { 
						totalSize = totalSize + entry[i].length();
						fileCount = fileCount + 1;
						if (entry[i].canRead()) {
							dlink = ahref + "downfile=" + name + "\">Download</a>";
							if (USE_POPUP) link = ahref + "file=" + name + "\" target=\"_blank\">"
									+ buf + "</a>";
							else link = ahref + "file=" + name + "\">" + buf + "</a>";
							if (entry[i].canWrite()) { // The file can be edited
								if (isPacked(name, true)) elink = ahref + "unpackfile=" + name
										+ "\">Unpack</a>";
								else {
									elink = ahref + "editfile=" + name + "\">Edit</a>";
									tlink = ahref + "tailfile=" + name + "\" target=\"_blank\">Tail</a>";
								}
							}
							else { 
								if (isPacked(name, true)) elink = ahref + "unpackfile=" + name
										+ "\">Unpack</a>";
								else {
									elink = ahref + "editfile=" + name + "\">View</a>";
									tlink = ahref + "tailfile=" + name + "\" target=\"_blank\">Tail</a>";
								}
							}
						}
						else {
							link = buf;
						}
					}
					String date = dateFormat.format(new Date(entry[i].lastModified()));
					out.println("<tr class=\"mouseout\" onmouseup=\"selrow(this, 2)\" "
							+ "onmouseover=\"selrow(this, 0);\" onmouseout=\"selrow(this, 1)\">");
					if (entry[i].canRead()) {
						out.println("<td align=center><input type=\"checkbox\" name=\"selfile\" value=\""
										+ name + "\" onmousedown=\"dis()\"></td>");
					}
					else {
						out.println("<td align=center><input type=\"checkbox\" name=\"selfile\" disabled></td>");
					}
					out.print("<td align=left> &nbsp;" + link + "</td>");
					if (entry[i].isDirectory()) out.print("<td>&nbsp;</td>");
					else {
						out.print("<td align=right title=\"" + entry[i].length() + " bytes\">"
								+ convertFileSize(entry[i].length()) + "</td>");
					}
					out.println("<td align=\"center\">" + type + "</td><td align=left> &nbsp;" + 
							date + "</td><td>" + 
							dlink + "</td>"); 
					if (!READ_ONLY)
						out.print ("<td>" + elink + "</td>"); 
					out.print ("<td>" + tlink + "</td>"); 
					out.println("</tr>");
				}
			}%>
	</table>
	<input type="checkbox" name="selall" onClick="AllFiles(this.form)">Select all
	<p align=center>
		<b title="<%=totalSize%> bytes">
		<%=convertFileSize(totalSize)%></b><b> in <%=fileCount%> files in <%= dir2linkdir((String) request.getAttribute("dir"), browser_name, sortMode)%>
		</b>
	</p>
		<input type="hidden" name="dir" value="<%=request.getAttribute("dir")%>">
		<input type="hidden" name="sort" value="<%=sortMode%>">
		<input title="Download selected files and directories as one zip file" class="button" id="but_Zip" type="Submit" name="Submit" value="<%=SAVE_AS_ZIP%>">
		<% if (!READ_ONLY) {%>
			<input title="Delete all selected files and directories incl. subdirs" class="button"  id="but_Del" type="Submit" name="Submit" value="<%=DELETE_FILES%>"
			onclick="return confirm('Do you really want to delete the entries?')">
		<% } %>
	<% if (!READ_ONLY) {%>
	<br />
		<input title="Enter new dir or filename or the relative or absolute path" class="textfield" type="text" onKeypress="event.cancelBubble=true;" id="text_Dir" name="cr_dir">
		<input title="Create a new directory with the given name" class="button" id="but_NDi" type="Submit" name="Submit" value="<%=CREATE_DIR%>">
		<input title="Create a new empty file with the given name" class="button" id="but_NFi" type="Submit" name="Submit" value="<%=CREATE_FILE%>">
		<input title="Move selected files and directories to the entered path" id="but_Mov" class="button" type="Submit" name="Submit" value="<%=MOVE_FILES%>">
		<input title="Copy selected files and directories to the entered path" id="but_Cop" class="button" type="Submit" name="Submit" value="<%=COPY_FILES%>">
		<input title="Rename selected file or directory to the entered name" id="but_Ren" class="button" type="Submit" name="Submit" value="<%=RENAME_FILE%>">
	<% } %>
	</form>
	<br />
	<div class="formular">
	<% if (ALLOW_UPLOAD) { %>
	<form class="formular2" action="<%= browser_name%>" enctype="multipart/form-data" method="POST">
		<input type="hidden" name="dir" value="<%=request.getAttribute("dir")%>">
		<input type="hidden" name="sort" value="<%=sortMode%>">
		<input type="file" class="textfield" onKeypress="event.cancelBubble=true;" name="myFile">
		<input title="Upload selected file to the current working directory" type="Submit" class="button" name="Submit" value="<%=UPLOAD_FILES%>"
		onClick="javascript:popUp('<%= browser_name%>')">
	</form>
	<%} %>
	<% if (NATIVE_COMMANDS) {%>
    <form class="formular2" action="<%= browser_name%>" method="POST">
		<input type="hidden" name="dir" value="<%=request.getAttribute("dir")%>">
		<input type="hidden" name="sort" value="<%=sortMode%>">
		<input type="hidden" name="command" value="">
		<input title="Launch command in current directory" type="Submit" class="button" id="but_Lau" name="Submit" value="<%=LAUNCH_COMMAND%>">
	</form>
	<% }%>
	<% if (SQL_CONSOLE) {%>
    <form class="formular2" action="<%= browser_name%>" method="POST">
		<input type="hidden" name="dir" value="<%=request.getAttribute("dir")%>">
		<input type="hidden" name="sort" value="<%=sortMode%>">
		<input type="hidden" name="sqlConsole" value="">
		<input title="Go to SQL console" type="Submit" class="button" id="but_Con" name="Submit" value="<%=SQL_LABEL%>">
	</form>
	<% }%>
    </div>
	<hr>
    <%}%>
</body>
</html><%
    }
%>