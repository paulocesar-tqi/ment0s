using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;

namespace CarregaImagem
{
    public class ImageWrapper
    {
        private const int X_INCREMENTER = 15;

        private Bitmap imagem;
        private int lastX;
        private List<Point> lstFirstPoints;
        private List<Point> lstLastPoints;

        public ImageWrapper(Bitmap bmp)
        {
            imagem = bmp;
            lstFirstPoints = GetFirstPoints();
            lstLastPoints = GetLastPoints();
        }

        public List<Point> FirstPoints
        {
            get { return this.lstFirstPoints; }
        }

        private List<Point> GetFirstPoints()
        {
            List<Point> lstPoints = new List<Point>();
            bool keepLoop = true;
            for (int x = 0; x < imagem.Width && keepLoop; x++)
            {
                for (int y = 0; y < imagem.Height; y++)
                {
                    Color c = imagem.GetPixel(x, y);
                    if (c.ToArgb() == Color.Black.ToArgb())
                    {
                        lstPoints.Add(new Point(x, y));
                    }
                }
                if (lstPoints.Count < 3)
                {
                    keepLoop = true;
                }
                else
                {
                    keepLoop = false;
                }
            }
            return lstPoints;
        }

        private List<Point> GetLastPoints()
        {
            List<Point> lstPoints = new List<Point>();
            bool keepLoop = true;
            for (int x = imagem.Width-1; x > 0 && keepLoop; x--)
            {
                for (int y = 0; y < imagem.Height; y++)
                {
                    Color c = imagem.GetPixel(x, y);
                    if (c.ToArgb() == Color.Black.ToArgb())
                    {
                        lstPoints.Add(new Point(x, y));                        
                    }
                }
                if (lstPoints.Count < 3)
                {
                    keepLoop = true;
                }
                else
                {
                    lastX = x;
                    keepLoop = false;
                }
            }
            return lstPoints;
        }

        public List<Point> GetNextPoints(Point point)
        {
            List<Point> lstPoints = new List<Point>();
            
            if (lstLastPoints.Contains(point))
                return lstPoints;

            int x = point.X + X_INCREMENTER;
            if (x > 160)
            {
                x = x;
            }
            if (x > lastX)
                return lstLastPoints;

            for (int y = 0; y < imagem.Height; y++)
            {
                Color col1 = imagem.GetPixel(x, y);
                if (col1.ToArgb() == Color.Black.ToArgb())
                {
                    lstPoints.Add(new Point(x, y));
                }

            }
            return lstPoints;

        }
        public bool CheckLine(Point start, Point end)
        {
            Point a = new Point(start);
            Point b = new Point(end);

            int dy = b.Y - a.Y;
            int dx = b.X - a.X;
            float t = (float)0.5;                      // offset for rounding

            if (Math.Abs(dx) > Math.Abs(dy))
            {          // slope < 1
                float m = (float)dy / (float)dx;      // compute s
                t += a.Y;
                dx = (dx < 0) ? -1 : 1;
                m *= dx;
                while (a.X != b.X)
                {
                    a.X += dx;                           // step to next x value
                    t += m;                             // add slope to y value

                    Color col1 = imagem.GetPixel(a.X, (int)t);
                    if (col1.ToArgb() != Color.Black.ToArgb())
                        return false;

                }

            }
            else
            {                                    // slope >= 1
                float m = (float)dx / (float)dy;      // compute slope
                t += a.X;
                dy = (dy < 0) ? -1 : 1;
                m *= dy;

                while (a.Y != b.Y)
                {
                    a.Y += dy;                           // step to next y value
                    t += m;                             // add slope to x value
                    Color col1 = imagem.GetPixel((int)t, a.Y);
                    if (col1.ToArgb() != Color.Black.ToArgb())
                        return false;
                }
            }
            return true;
        }

        internal void Paint(Path path)
        {
            using (Graphics g = Graphics.FromImage(imagem))
            {
                for (int i = 0; i < path.Points.Count-1; i++)
                {
                    Point p = path.Points[i];
                    Point p2 = path.Points[i + 1];
                    g.DrawLine(new Pen(Color.White), new PointF(p.X, p.Y), new PointF(p2.X, p2.Y));
                }
                
            }
        }
    }
}
