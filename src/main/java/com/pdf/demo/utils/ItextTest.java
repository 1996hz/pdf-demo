package com.pdf.demo.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.util.List;

/**
 * 在PDF中添加文字或者图片
 * @Author huangzhen
 * @create 2020/12/4 15:09
 */
public class ItextTest {

    public static void main(String[] args) {
        try {
            String TemplatePDF = "D:\\安装说明.pdf"; //测试demo地址
            String outFile = "D:\\new.pdf"; //生成新的pdf的路径
            PdfReader reader = new PdfReader(TemplatePDF);
            PdfStamper ps = new PdfStamper(reader, new FileOutputStream(outFile)); // 生成的输出流

            AcroFields s = ps.getAcroFields();
// 插入文字
//          insertText(ps, s);
// 插入图片
            insertImage(ps, s);
            ps.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入图片
     *
     * @param ps
     * @param s
     */
    public static void insertImage(PdfStamper ps, AcroFields s) {


        try {
//            List<AcroFields.FieldPosition> list = s.getFieldPositions("QR_CODE");
//            Rectangle signRect = list.get(0).position;
            //地址暂时自己先写
            Rectangle signRect = new Rectangle(20.156f,5.92f,40.156f,40.156f);


            Image image = Image.getInstance("D:\\pdf.jpg");
            //页码
            PdfContentByte under = ps.getOverContent(1);
            float x = signRect.getLeft();
            float y = signRect.getBottom();
            System.out.println(x);
            System.out.println(y);
            image.setAbsolutePosition(x, y);
            image.scaleToFit(signRect.getWidth(), signRect.getHeight());


            under.addImage(image);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    /**
     * 创建Chunk
     *
     * @return
     */
    public static Chunk CreateChunk() {
        BaseFont bfChinese;
        Chunk chunk = null;
        try {
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            Font fontChinese = new Font(bfChinese, 10 * 4 / 3);
            chunk = new Chunk("张三", fontChinese);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return chunk;
    }

    /**
     * 插入文本
     *
     * @return
     * @author WangMeng
     * @date 2016年6月16日
     */
    public static void insertText(PdfStamper ps, AcroFields s) {
        List<AcroFields.FieldPosition> list = s.getFieldPositions("CONNECT_NAME");
        Rectangle rect = list.get(0).position;


        PdfContentByte cb = ps.getOverContent(1);
        PdfPTable table = new PdfPTable(1);
        float tatalWidth = rect.getRight() - rect.getLeft() - 1;
        table.setTotalWidth(tatalWidth);


        PdfPCell cell = new PdfPCell(new Phrase(CreateChunk()));
        cell.setFixedHeight(rect.getTop() - rect.getBottom() - 1);
        cell.setBorderWidth(0);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setLeading(0, (float) 1.1);


        table.addCell(cell);
        table.writeSelectedRows(0, -1, rect.getLeft(), rect.getTop(), cb);
    }

}
