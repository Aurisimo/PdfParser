package com.example.pdfparser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.tom_roush.pdfbox.cos.COSDocument
import com.tom_roush.pdfbox.io.RandomAccessBufferedFileInputStream;
import com.tom_roush.pdfbox.pdfparser.PDFParser;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parsePdf_1_8_10()

    }

    fun parsePdf_1_8_10() {
        PDFBoxResourceLoader.init(getApplicationContext());

        var parser: PDFParser? = null
        var pdDoc: PDDocument? = null
        var cosDoc: COSDocument? = null
        val pdfStripper: PDFTextStripper
        val parsedText: String

        val file = assets.open("document.pdf")
        try {
            parser = PDFParser(RandomAccessBufferedFileInputStream(file))
            parser.parse()
            cosDoc = parser.getDocument()
            pdfStripper = PDFTextStripper()
            pdDoc = PDDocument(cosDoc)
            parsedText = pdfStripper.getText(pdDoc)
            println(parsedText)
        } catch (e: Exception) {
            e.printStackTrace()
            try {
                if (cosDoc != null) cosDoc.close()
                if (pdDoc != null) pdDoc.close()
            } catch (e1: Exception) {
                e1.printStackTrace()
            }
        }
    }
}