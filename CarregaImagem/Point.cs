using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;

namespace CarregaImagem
{
    public class Point
    {        

        private int x;
        private int y;
        private List<Point> lstAncessor = new List<Point>();

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

        public List<Point> Ancessors
        {
            get
            {
                return lstAncessor;
            }
        }

        public void AddAncessor(Point p)
        {
            lstAncessor.Add(p);
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
