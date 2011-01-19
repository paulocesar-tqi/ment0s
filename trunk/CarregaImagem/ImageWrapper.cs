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
        
        private Dictionary<int, List<Point>> memoryPoints = new Dictionary<int, List<Point>>();

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
                    if (IsGoodPixel(x, y))
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
                    if (IsGoodPixel(x, y))
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
            if (x > lastX)
                return lstLastPoints;

            if (memoryPoints.ContainsKey(x))
            {
                return memoryPoints[x];
            }

            for (int y = 0; y < imagem.Height; y++)
            {
                if (IsGoodPixel(x, y))
                {
                    lstPoints.Add(new Point(x, y));
                }

            }
            memoryPoints[x] = lstPoints;
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

                    int rounded = (int)t;
                    if (!IsGoodPixel(a.X, rounded))
                    {
                        rounded = (int)Math.Ceiling(t);
                        if (!IsGoodPixel(a.X, rounded))
                        {
                            rounded = (int)t-1;
                            if (!IsGoodPixel(a.X, rounded))
                                return false;
                        }
                    }

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
                    if (!IsGoodPixel((int)t, a.Y))
                        return false;
                }
            }
            return true;
        }

        public void ClearImage()
        {
            Log.WriteLine("Clear Image: " + DateTime.Now);
            for (int x = 1; x < imagem.Width-1; x++)
            {
                for (int y = 1; y < imagem.Height-1; y++)
                {
                    if (IsGoodPixel(x,y) && IsAlonePixel(x, y))
                    {
                        imagem.SetPixel(x, y, Color.White);
                    }
                }
            }
            Log.WriteLine("Clear Image: " + DateTime.Now);
        }

        private bool IsGoodPixel(int x, int y)
        {
            Color col1 = imagem.GetPixel(x, y);
            return (col1.ToArgb() == Color.Black.ToArgb());
        }

        private bool IsAlonePixel(int x, int y)
        {
            int thresold = 1;
            int count = 0;
            count += IsGoodPixel(x + 1, y) ? 1 : 0;
            count += IsGoodPixel(x, y + 1) ? 1 : 0;
            count += IsGoodPixel(x - 1, y) ? 1 : 0;
            count += IsGoodPixel(x, y - 1) ? 1 : 0;
            
            count += IsGoodPixel(x - 1, y - 1) ? 1 : 0;
            count += IsGoodPixel(x - 1, y + 1) ? 1 : 0;
            count += IsGoodPixel(x + 1, y + 1) ? 1 : 0;
            count += IsGoodPixel(x + 1, y - 1) ? 1 : 0;

            return (count <= thresold);
        }

        internal void Paint(IEnumerable<Path> lstPath)
        {
            Log.WriteLine("Paint Image: " + DateTime.Now);
            using (Graphics g = Graphics.FromImage(imagem))
            {
                foreach (Path path in lstPath)
                {
                    for (int i = 0; i < path.Points.Count - 1; i++)
                    {
                        Point p = path.Points[i];
                        Point p2 = path.Points[i + 1];
                        g.DrawLine(new Pen(Color.White), new PointF(p.X, p.Y), new PointF(p2.X, p2.Y));
                    }
                }
            }
            Log.WriteLine("Paint Image: " + DateTime.Now);
        }
    }
}
