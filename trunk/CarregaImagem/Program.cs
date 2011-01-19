using System;
using System.Collections.Generic;
using System.Windows.Forms;
using System.IO;
using System.Drawing;

namespace CarregaImagem
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        static void Main(string[] args)
        {
            /*
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1());
            */
            if (args.Length < 1)
            {
                Console.WriteLine("Informar o diretório: ");
                Console.WriteLine(@"Example ListFiles.exe c:\test");
            }
            else
            {
                try
                {
                    Console.WriteLine(args[0]);
                    DirectoryInfo di = new DirectoryInfo(args[0]);
                    FileInfo[] files = null;
                    if (args.Length == 1) {
                        files = di.GetFiles("*.bmp");
                    }
                    if (files != null && files.Length > 0)
                    {
                        string newPrefix = di.FullName + "\\" + DateTime.Now.Ticks;
                        foreach (FileInfo file in files)
                        {
                            Bitmap imagem = (Bitmap)Bitmap.FromFile(file.FullName);

                            ImageWrapper wrapper = new ImageWrapper(imagem);
                            Graph graph = new Graph(wrapper);

                            graph.FindAllPaths(wrapper.FirstPoints);

                            IEnumerable<Path> lstPaths = graph.Paths;
                            foreach (Path p in lstPaths)
                            {
                                wrapper.Paint(p);
                            }
                            imagem.Save(newPrefix + file.Name);
                            Console.WriteLine(file.Name + " -> " + newPrefix + file.Name);
                        }
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
}