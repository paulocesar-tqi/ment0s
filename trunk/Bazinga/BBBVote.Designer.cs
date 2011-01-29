namespace BBBVote
{
    partial class BBBVote
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(BBBVote));
            this.voteButton = new System.Windows.Forms.Button();
            this.textBox = new System.Windows.Forms.TextBox();
            this.statusStrip = new System.Windows.Forms.StatusStrip();
            this.toolStripStatusLabel = new System.Windows.Forms.ToolStripStatusLabel();
            this.numVotesFailLabel = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.bannerPictureBox = new System.Windows.Forms.PictureBox();
            this.checkBoxAutocomplete = new System.Windows.Forms.CheckBox();
            this.pictureBox = new System.Windows.Forms.PictureBox();
            this.numVotesOkLabel = new System.Windows.Forms.Label();
            this.labelTotalVotos = new System.Windows.Forms.Label();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.refreshButton = new System.Windows.Forms.Button();
            this.statusStrip.SuspendLayout();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.bannerPictureBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // voteButton
            // 
            this.voteButton.Enabled = false;
            this.voteButton.Location = new System.Drawing.Point(231, 62);
            this.voteButton.Name = "voteButton";
            this.voteButton.Size = new System.Drawing.Size(42, 20);
            this.voteButton.TabIndex = 1;
            this.voteButton.Text = "Votar";
            this.voteButton.UseVisualStyleBackColor = true;
            this.voteButton.Click += new System.EventHandler(this.vote_Click);
            // 
            // textBox
            // 
            this.textBox.AutoCompleteMode = System.Windows.Forms.AutoCompleteMode.SuggestAppend;
            this.textBox.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.CustomSource;
            this.textBox.CharacterCasing = System.Windows.Forms.CharacterCasing.Lower;
            this.textBox.Location = new System.Drawing.Point(76, 62);
            this.textBox.Name = "textBox";
            this.textBox.Size = new System.Drawing.Size(149, 20);
            this.textBox.TabIndex = 0;
            this.textBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.textBox_KeyDown);
            // 
            // statusStrip
            // 
            this.statusStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripStatusLabel});
            this.statusStrip.Location = new System.Drawing.Point(0, 282);
            this.statusStrip.Name = "statusStrip";
            this.statusStrip.Size = new System.Drawing.Size(284, 22);
            this.statusStrip.SizingGrip = false;
            this.statusStrip.TabIndex = 6;
            this.statusStrip.Text = "statusStrip1";
            // 
            // toolStripStatusLabel
            // 
            this.toolStripStatusLabel.Name = "toolStripStatusLabel";
            this.toolStripStatusLabel.Size = new System.Drawing.Size(73, 17);
            this.toolStripStatusLabel.Text = "Aguardando";
            // 
            // numVotesFailLabel
            // 
            this.numVotesFailLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.numVotesFailLabel.Location = new System.Drawing.Point(58, 232);
            this.numVotesFailLabel.Name = "numVotesFailLabel";
            this.numVotesFailLabel.Size = new System.Drawing.Size(68, 31);
            this.numVotesFailLabel.TabIndex = 7;
            this.numVotesFailLabel.Text = "0";
            this.numVotesFailLabel.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(110, 266);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(34, 13);
            this.label2.TabIndex = 8;
            this.label2.Text = "Total:";
            this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.bannerPictureBox);
            this.groupBox1.Controls.Add(this.checkBoxAutocomplete);
            this.groupBox1.Controls.Add(this.pictureBox);
            this.groupBox1.Controls.Add(this.textBox);
            this.groupBox1.Controls.Add(this.voteButton);
            this.groupBox1.Location = new System.Drawing.Point(0, -2);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(284, 221);
            this.groupBox1.TabIndex = 15;
            this.groupBox1.TabStop = false;
            // 
            // bannerPictureBox
            // 
            this.bannerPictureBox.Location = new System.Drawing.Point(9, 89);
            this.bannerPictureBox.Name = "bannerPictureBox";
            this.bannerPictureBox.Size = new System.Drawing.Size(264, 122);
            this.bannerPictureBox.TabIndex = 2;
            this.bannerPictureBox.TabStop = false;
            // 
            // checkBoxAutocomplete
            // 
            this.checkBoxAutocomplete.AutoSize = true;
            this.checkBoxAutocomplete.Checked = true;
            this.checkBoxAutocomplete.CheckState = System.Windows.Forms.CheckState.Checked;
            this.checkBoxAutocomplete.Location = new System.Drawing.Point(8, 65);
            this.checkBoxAutocomplete.Name = "checkBoxAutocomplete";
            this.checkBoxAutocomplete.Size = new System.Drawing.Size(59, 17);
            this.checkBoxAutocomplete.TabIndex = 0;
            this.checkBoxAutocomplete.Text = "Sugerir";
            this.checkBoxAutocomplete.UseVisualStyleBackColor = true;
            this.checkBoxAutocomplete.CheckedChanged += new System.EventHandler(this.checkBoxAutocomplete_CheckedChanged);
            // 
            // pictureBox
            // 
            this.pictureBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pictureBox.Location = new System.Drawing.Point(76, 11);
            this.pictureBox.Name = "pictureBox";
            this.pictureBox.Size = new System.Drawing.Size(197, 45);
            this.pictureBox.TabIndex = 1;
            this.pictureBox.TabStop = false;
            // 
            // numVotesOkLabel
            // 
            this.numVotesOkLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.numVotesOkLabel.Location = new System.Drawing.Point(153, 232);
            this.numVotesOkLabel.Name = "numVotesOkLabel";
            this.numVotesOkLabel.Size = new System.Drawing.Size(60, 31);
            this.numVotesOkLabel.TabIndex = 18;
            this.numVotesOkLabel.Text = "0";
            this.numVotesOkLabel.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelTotalVotos
            // 
            this.labelTotalVotos.AutoSize = true;
            this.labelTotalVotos.Location = new System.Drawing.Point(141, 266);
            this.labelTotalVotos.Name = "labelTotalVotos";
            this.labelTotalVotos.Size = new System.Drawing.Size(13, 13);
            this.labelTotalVotos.TabIndex = 19;
            this.labelTotalVotos.Text = "0";
            this.labelTotalVotos.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // pictureBox2
            // 
            this.pictureBox2.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox2.Image")));
            this.pictureBox2.Location = new System.Drawing.Point(219, 225);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(52, 50);
            this.pictureBox2.TabIndex = 17;
            this.pictureBox2.TabStop = false;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::BBBVote.Properties.Resources.new_fuu;
            this.pictureBox1.Location = new System.Drawing.Point(11, 225);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(41, 52);
            this.pictureBox1.TabIndex = 16;
            this.pictureBox1.TabStop = false;
            // 
            // refreshButton
            // 
            this.refreshButton.BackgroundImage = global::BBBVote.Properties.Resources.refresh1;
            this.refreshButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Center;
            this.refreshButton.Location = new System.Drawing.Point(8, 9);
            this.refreshButton.Name = "refreshButton";
            this.refreshButton.Size = new System.Drawing.Size(56, 45);
            this.refreshButton.TabIndex = 2;
            this.refreshButton.UseVisualStyleBackColor = true;
            this.refreshButton.Click += new System.EventHandler(this.refreshButton_Click);
            // 
            // BBBVote
            // 
            this.AcceptButton = this.voteButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 304);
            this.Controls.Add(this.labelTotalVotos);
            this.Controls.Add(this.numVotesOkLabel);
            this.Controls.Add(this.pictureBox2);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.numVotesFailLabel);
            this.Controls.Add(this.statusStrip);
            this.Controls.Add(this.refreshButton);
            this.Controls.Add(this.groupBox1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "BBBVote";
            this.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide;
            this.Text = "Bazinga!";
            this.Load += new System.EventHandler(this.BBBVote_Load);
            this.Shown += new System.EventHandler(this.BBBVote_Shown);
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.BBBVote_FormClosing);
            this.statusStrip.ResumeLayout(false);
            this.statusStrip.PerformLayout();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.bannerPictureBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button voteButton;
        private System.Windows.Forms.PictureBox pictureBox;
        private System.Windows.Forms.TextBox textBox;
        private System.Windows.Forms.Button refreshButton;
        private System.Windows.Forms.StatusStrip statusStrip;
        private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel;
        private System.Windows.Forms.Label numVotesFailLabel;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.CheckBox checkBoxAutocomplete;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.PictureBox pictureBox2;
        private System.Windows.Forms.Label numVotesOkLabel;
        private System.Windows.Forms.Label labelTotalVotos;
        private System.Windows.Forms.PictureBox bannerPictureBox;
    }
}

