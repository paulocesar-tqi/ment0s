using System;
using System.Collections.Generic;
using System.Text;

namespace CarregaImagem
{
    public class Point
    {
        private int x;
        private int y;

        public Point(int x, int y)
        {
            X = x;
            Y = y;
        }

        public Point(Point p)
        {
            X = p.X;
            Y = p.Y;
        }

        public int X
        {
            get { return x; }
            set { x = value; }
        }

        public int Y
        {
            get { return y; }
            set { y = value; }
        }


        public override bool Equals(object obj)
        {
            if (obj == null || GetType() != obj.GetType())
            {
                return false;
            }

            Point p = (Point)obj;
            if (this.x == p.x && this.y == p.y)
                return true;
            return false;

        }

        public override int GetHashCode()
        {
            return this.x * 100000 + this.y;
        }


    }
}
