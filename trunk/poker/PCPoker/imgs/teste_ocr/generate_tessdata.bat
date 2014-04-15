tesseract eng.matrx554x32.exp0.png eng.matrx554x32.exp0.box nobatch box.train
unicharset_extractor.exe eng.matrx554x32.exp0.box
shapeclustering -F font.properties -U unicharset eng.matrx554x32.exp0.box.tr
mftraining -F font.properties -U unicharset -O eng.unicharset eng.matrx554x32.exp0.box.tr
cntraining eng.matrx554x32.exp0.box.tr
ren inttemp eng.inttemp
ren normproto eng.normproto
ren pffmtable eng.pffmtable
ren shapetable eng.shapetable
combine_tessdata.exe eng.