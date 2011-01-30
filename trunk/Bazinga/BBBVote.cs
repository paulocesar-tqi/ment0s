using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

using System.Net;
using System.Net.Sockets;
using System.IO;
using System.Web;

using System.Media;

using System.Drawing.Imaging;
using System.IO.Compression;

namespace BBBVote
{
    public partial class BBBVote : Form
    {
        // Application
        public   string version = "2.1";
        private  string siteURL;
        private const string versionURL = "http://109.169.62.142/bazinga/data2.txt";
        private const string dicionarioURL = "http://109.169.62.142/bazinga/captchas.gz";
        private  string postChaptchasURL;
        
        // BBB voting
        private string captchaURL;
        private string voteURL;
        
        // Pool specific
        private string voteOption;
        private string poolId;
        private string paramVotacao ;
        private string urlVotacao ;
        private string refererURL ;
        private string successURL ;
        private string errorURL;
        private string blockURL;
        private string imageURL;

        private struct VoteEntry
        {
            public Image image;
            public CookieCollection cookies;
            public string answer;
        };

        private VoteEntry voteEntry;
        private int numVotesOk;
        private int numVotesFail;

        public BBBVote()
        {
            InitializeComponent();
        }

        private void BBBVote_Load(object sender, EventArgs e)
        {
            Text += " (versão " + version + ")";
            LoadCaptchas();
        }

        private void vote_Click(object sender, EventArgs e)
        {
            SendVote();
        }

        private void refreshButton_Click(object sender, EventArgs e)
        {
             NewSession();
        }

        private void linkLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            LaunchSite();
        }

        private void BBBVote_Shown(object sender, EventArgs e)
        {
            System.Net.ServicePointManager.Expect100Continue = true;

            CheckData();
            NewSession();
            DisplayStatus("Aguardando...");
        }

        private void BBBVote_FormClosing(object sender, FormClosingEventArgs e)
        {
            SaveCaptchas();

        }

        private void textBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Space)
            {
                e.SuppressKeyPress = true;
                voteButton.PerformClick();
            }
        }

        private void checkBoxAutocomplete_CheckedChanged(object sender, EventArgs e)
        {
            if (checkBoxAutocomplete.Checked)
            {
                textBox.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            }
            else
            {
                textBox.AutoCompleteMode = AutoCompleteMode.None;
            }
        }
 
        private void CheckData()
        {
            try
            {
                DisplayStatus("Checando se esta versão é válida");

                HttpWebRequest http = (HttpWebRequest)HttpWebRequest.Create(versionURL);
                http.KeepAlive = false;
                http.Timeout = 10 * 1000;
                HttpWebResponse response = (HttpWebResponse)http.GetResponse();

                StreamReader reader = new StreamReader(response.GetResponseStream());
                string data = reader.ReadToEnd();
                response.Close();

                string[] dados = data.Split(';');
                
                string newVersion = dados[0];
                siteURL = dados[1];
                postChaptchasURL = dados[2];
                captchaURL = dados[3];
                voteURL = dados[4];
                voteOption = dados[5];
                poolId = dados[6];
                paramVotacao = dados[7];
                urlVotacao = dados[8];
                refererURL = dados[9];
                successURL = dados[10];
                errorURL = dados[11];
                blockURL = dados[12];
                imageURL = dados[13];
                string programActive = dados[14];


                if (!programActive.Equals("1")) 
                {
                    MessageBox.Show("Esta versão não é mais válida, aguarde uma nova versão.", "Perdeu!", MessageBoxButtons.OK);
                    Application.Exit();
                }


                if (newVersion != version)
                {
                    MessageBox.Show("Uma nova versão está disponível, clique em 'OK' para fazer o download.", "Atualizador", MessageBoxButtons.OK);
                    LaunchSite();
                    
                    Application.Exit();
                }

                RefreshBanner();
                DownloadCaptchas();

            }
            catch
            {
                MessageBox.Show("Erro ao se conectar no servidor, verifique sua conexão.", "Perdeu!", MessageBoxButtons.OK);
                Application.Exit();
            }
        }

        private void RefreshBanner()
        {
            // Query for captcha image and cookies (JSESSIONID)
            HttpWebRequest http = (HttpWebRequest)HttpWebRequest.Create(imageURL);
            http.Accept = "image/*";
            http.Timeout = 5 * 1000;
            HttpWebResponse response = (HttpWebResponse)http.GetResponse();

            // Update UI
            bannerPictureBox.Image = Image.FromStream(response.GetResponseStream());
            response.Close();
        }

        private void LoadCaptchas()
        {
            DisplayStatus("Carregando captchas...");
            try
            {
                textBox.AutoCompleteCustomSource = new AutoCompleteStringCollection();
                StreamReader reader = new StreamReader("captchas.dat");
                string contents = reader.ReadToEnd();
                textBox.AutoCompleteCustomSource.AddRange(contents.Split(new Char[] { '\r', '\n' }, StringSplitOptions.RemoveEmptyEntries));
                reader.Close();
            }
            catch
            {

            }
        }

        private void SaveCaptchas()
        {
            DisplayStatus("Salvando captchas...");

            string[] entries = new string[textBox.AutoCompleteCustomSource.Count];
            textBox.AutoCompleteCustomSource.CopyTo(entries, 0);

            Array.Sort(entries);

            using (StreamWriter writer = new StreamWriter("captchas.dat"))
            {
                List<String> uniqueList = new List<string>();
                foreach (string entry in entries)
                {
                    if (!uniqueList.Contains(entry))
                    {
                        uniqueList.Add(entry);
                        writer.WriteLine(entry);
                    }
                    
                }
                writer.Flush();
            }

            uploadFile(postChaptchasURL, "captchas.dat", "captchas" + DateTime.Now.Ticks + "_" + numVotesOk + ".gz", "ftpuser", "Ronaldo2011.");

            DisplayStatus("Captchas enviados...");
        }

        private void NewSession()
        {
            try
            {
                // Query for captcha image and cookies (JSESSIONID)
                HttpWebRequest http = (HttpWebRequest)HttpWebRequest.Create(captchaURL);
                http.Referer = refererURL;
                http.Accept = "image/*";
                http.Timeout = 5 * 1000;
                http.CookieContainer = new CookieContainer();

                HttpWebResponse response = (HttpWebResponse)http.GetResponse();

                voteEntry.image = Image.FromStream(response.GetResponseStream());
                voteEntry.answer = "";
                voteEntry.cookies = response.Cookies;

                response.Close();

                // Update UI
                pictureBox.Image = voteEntry.image;
                textBox.Text = voteEntry.answer;
                voteButton.Enabled = true;
                textBox.Focus();

                //Save Image
                //voteEntry.image.Save("captcha_" + DateTime.Now.Ticks + ".jpg");
            }
            catch
            {
                pictureBox.Image = pictureBox.ErrorImage;
                voteButton.Enabled = false;
                DisplayError("Erro ao conectar ao servidor!");
            }
        }

        private void SendVote()
        {
            // Just refresh if empty
            if (textBox.Text == "")
            {
                DisplayStatus("Aguardando...");
                NewSession();
                return;
            }

            voteButton.Enabled = false;
            voteEntry.answer = textBox.Text;
            DisplayStatus("Enviando Voto...");

            try
            {
                // POST the cookies and post data
                HttpWebRequest http = (HttpWebRequest)HttpWebRequest.Create(voteURL);
                http.Method = "POST";
                http.Referer = refererURL;
                http.Timeout = 5 * 1000;
                http.CookieContainer = new CookieContainer();
                http.CookieContainer.Add(voteEntry.cookies);

                string postData = "poll_id=" + poolId +
                                  "&param_votacao=" + paramVotacao +
                                  "&url_transcao=" + HttpUtility.UrlEncode(urlVotacao) +
                                  "&success_url=" + HttpUtility.UrlEncode(successURL) +
                                  "&error_url=" + HttpUtility.UrlEncode(errorURL) +
                                  "&block_url=" + HttpUtility.UrlEncode(blockURL) +
                                  "&opt=" + voteOption +
                                  "&answer=" + voteEntry.answer +
                                  "&" + voteEntry.cookies["check"];
                

                ASCIIEncoding encoding = new ASCIIEncoding();
                byte[] byte1 = encoding.GetBytes(postData);

                http.ContentType = "application/x-www-form-urlencoded";

                http.ContentLength = postData.Length;
                Stream stream = http.GetRequestStream();
                stream.Write(byte1, 0, byte1.Length);
                stream.Close();

                // Read the response to check for ending of votation
                HttpWebResponse response = (HttpWebResponse)http.GetResponse();
                StreamReader responseStream = new StreamReader(response.GetResponseStream());
                string responseData = responseStream.ReadToEnd();

                if (responseData.Contains("encerrada"))
                {
                    DisplayError("Votação encerrada!");
                    numVotesFail++;
                    numVotesFailLabel.Text = numVotesFail.ToString();
                    labelTotalVotos.Text = (numVotesFail + numVotesOk).ToString();

                }
                else
                {
                    // If the server forwarded to successURL
                    if (response.ResponseUri.AbsoluteUri == successURL)
                    {
                        numVotesOk++;
                        numVotesOkLabel.Text = numVotesOk.ToString();
                        labelTotalVotos.Text = (numVotesFail + numVotesOk).ToString();

                        if (!textBox.AutoCompleteCustomSource.Contains(voteEntry.answer))
                            textBox.AutoCompleteCustomSource.Add(voteEntry.answer);

                        DisplayStatus("Voto enviado!");

                        //envia os captchas a cada 50
                        if (numVotesOk % 50 == 0)
                            SaveCaptchas();
                    }
                    else if (response.ResponseUri.AbsoluteUri == errorURL)
                    {
                        DisplayError("Captcha inválido!");
                        numVotesFail++;
                        numVotesFailLabel.Text = numVotesFail.ToString();
                        labelTotalVotos.Text = (numVotesFail + numVotesOk).ToString();
                    }

                }

                responseStream.Close();
                response.Close();
            }
            catch
            {
                DisplayError("Erro ao conectar ao servidor!");
                numVotesFail++;
                numVotesFailLabel.Text = numVotesFail.ToString();
                labelTotalVotos.Text = (numVotesFail + numVotesOk).ToString();
            }

            NewSession();
        }

        private void LaunchSite()
        {
            System.Diagnostics.Process.Start(siteURL);
        }

        private void DisplayStatus(string message)
        {
            toolStripStatusLabel.Text = message;
            toolStripStatusLabel.ForeColor = Color.Black;
            Refresh();
        }

        private void DisplayError(string message)
        {
            toolStripStatusLabel.Text = message;
            toolStripStatusLabel.ForeColor = Color.Red;
            Refresh();
        }

        private void uploadFile(string FTPAddress, string localFilePath, string remoteFilename, string username, string password)
        {

            //Create FTP request
            FtpWebRequest request = (FtpWebRequest)FtpWebRequest.Create(FTPAddress + "/" + remoteFilename);

            request.Method = WebRequestMethods.Ftp.UploadFile;
            request.Credentials = new NetworkCredential(username, password);
            request.UsePassive = true;
            request.UseBinary = true;
            request.KeepAlive = false;

            //Load the file
            //Compress the file
            String fileContent = File.ReadAllText(localFilePath);
            byte[] buffer = CompressStringToFile("test.gz", fileContent);

            //Upload file
            Stream reqStream = request.GetRequestStream();
            reqStream.Write(buffer, 0, buffer.Length);
            reqStream.Close();
        }

        private void DownloadCaptchas()
        {
            DisplayStatus("Baixando dicionário...");

            HttpWebRequest http = (HttpWebRequest)HttpWebRequest.Create(dicionarioURL);
            http.KeepAlive = false;
            http.Timeout = 10 * 1000;
            HttpWebResponse response = (HttpWebResponse)http.GetResponse();

            GZipStream gzDecompressed = new GZipStream(response.GetResponseStream(), CompressionMode.Decompress, true);

            StreamReader reader = new StreamReader(gzDecompressed);
            string contents = reader.ReadToEnd();
            textBox.AutoCompleteCustomSource.AddRange(contents.Split(new Char[] { '\r', '\n' }, StringSplitOptions.RemoveEmptyEntries));

            DisplayStatus("Dicionário Carregado...");
            reader.Close();
            response.Close();
        }


        private byte[] CompressStringToFile(string fileName, string value)
        {
            // A.
            // Write string to temporary file.
            string temp = Path.GetTempFileName();
            File.WriteAllText(temp, value);

            // B.
            // Read file into byte array buffer.
            byte[] b;
            using (FileStream f = new FileStream(temp, FileMode.Open))
            {
                b = new byte[f.Length];
                f.Read(b, 0, (int)f.Length);
            }

            // C.
            // Use GZipStream to write compressed bytes to target file.
            using (FileStream f2 = new FileStream(fileName, FileMode.Create))
            using (GZipStream gz = new GZipStream(f2, CompressionMode.Compress, false))
                gz.Write(b, 0, b.Length);

            using (FileStream f = new FileStream(fileName, FileMode.Open))
            {
                b = new byte[f.Length];
                f.Read(b, 0, (int)f.Length);
            }
            return b;
        }
   }
}