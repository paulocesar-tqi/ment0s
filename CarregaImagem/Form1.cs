using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace CarregaImagem
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                OpenFileDialog open = new OpenFileDialog();
                open.Filter = "Image Files(*.jpg; *.jpeg; *.gif; *.bmp)|*.jpg; *.jpeg; *.gif; *.bmp";
                if (open.ShowDialog() == DialogResult.OK)
                {
                    Bitmap imagem = (Bitmap)Bitmap.FromFile(open.FileName);

                    ImageWrapper wrapper = new ImageWrapper(imagem);
                    Graph graph = new Graph(wrapper);

                    graph.FindAllPaths(wrapper.FirstPoints);

                    List<Point> lastPoints = graph.LastPoints;
                    wrapper.Paint(lastPoints);
                    wrapper.ClearImage();
                    wrapper.Repopulate();
                    //txtPaths.Text = sb.ToString();

/*
                    using (Graphics g = Graphics.FromImage(imagem))
                    {
                        g.DrawLine(new Pen(Color.Red), new Point(18, lastBlackPixel), new Point(33, lastBlackPixel));
                    } 
                    */

                    pictureBox.Image = imagem;
                    imagem.Save("c1.bmp");
                }
            }
            catch (Exception)
            {
                throw new ApplicationException("Failed loading image");
            }
        }




    }
}