namespace WinServerDDE
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.outBox = new System.Windows.Forms.TextBox();
            this.button1 = new System.Windows.Forms.Button();
            this.timer_ack = new System.Windows.Forms.Timer(this.components);
            this.SuspendLayout();
            // 
            // outBox
            // 
            this.outBox.Location = new System.Drawing.Point(12, 83);
            this.outBox.Multiline = true;
            this.outBox.Name = "outBox";
            this.outBox.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.outBox.Size = new System.Drawing.Size(795, 460);
            this.outBox.TabIndex = 0;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(12, 25);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 1;
            this.button1.Text = "Start!";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // timer_ack
            // 
            this.timer_ack.Interval = 1000;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(819, 555);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.outBox);
            this.Name = "Form1";
            this.Text = "DDEServer";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox outBox;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Timer timer_ack;
    }
}

