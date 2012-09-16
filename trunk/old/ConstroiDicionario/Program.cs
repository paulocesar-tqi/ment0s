using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.IO.Compression;

namespace ConstroiDicionario
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                DirectoryInfo di = new DirectoryInfo(".");
                FileInfo[] files = di.GetFiles("*.gz");
                if (files != null && files.Length > 0)
                {
                    int totalVotos = 0;
                    List<String> uniqueList = new List<string>();
                    foreach (FileInfo file in files)
                    {
                        //Soma os votos
                        string[] sub = file.Name.Split('.');
                        string[] sub2 = sub[0].Split('_');
                        totalVotos += Int32.Parse(sub2[1]);

                        //Descompacta o arquivo
                        GZipStream gzDecompressed = new GZipStream(file.OpenRead(), CompressionMode.Decompress, true);
                        StreamReader reader = new StreamReader(gzDecompressed);
                        string contents = reader.ReadToEnd();
                        string[] entries = contents.Split(new Char[] { '\r', '\n' }, StringSplitOptions.RemoveEmptyEntries);

                        foreach (string entry in entries)
                        {
                            if (!uniqueList.Contains(entry))
                            {
                                uniqueList.Add(entry);
                            }

                        }
                        reader.Close();
                    }
                    if (uniqueList.Count > 0)
                    {
                        using (StreamWriter writer = new StreamWriter("captchas.txt"))
                        {
                            uniqueList.Sort();
                            foreach (string entry in uniqueList)
                            {
                                writer.WriteLine(entry);
                            }
                            writer.Flush();
                        }

                    }
                    Console.WriteLine("Total de votos: " + totalVotos);
                    Console.ReadLine();
                }
                else
                {
                    Console.WriteLine("No files");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception occured");
                Console.WriteLine(ex.Message);
                Console.WriteLine();
            }
        }

    }
}
