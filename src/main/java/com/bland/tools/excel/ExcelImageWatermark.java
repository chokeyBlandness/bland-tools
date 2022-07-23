package com.bland.tools.excel;

import com.bland.tools.util.StringImageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author cqk
 * @since 2022/7/23
 */
public class ExcelImageWatermark {

    public OutputStream writeStringImage(InputStream inputStream, String jsonString, String hiddenInfo) throws Exception {
        BufferedImage image = StringImageUtils.createBufferedImage(jsonString, 300, 100);
        // 导出到字节流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);

        // 在png中写入隐藏信息
        if (StringUtils.isNoneBlank(hiddenInfo)) {
            os.write(hiddenInfo.getBytes(StandardCharsets.UTF_8));
        }

        // 读取excel文件
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        int pictureIdx = workbook.addPicture(os.toByteArray(), Workbook.PICTURE_TYPE_PNG);

        POIXMLDocumentPart poixmlDocumentPart = workbook.getAllPictures().get(pictureIdx);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
            XSSFSheet sheet = workbook.getSheetAt(i);
            PackagePartName ppn = poixmlDocumentPart.getPackagePart().getPartName();
            String relType = XSSFRelation.IMAGES.getRelation();
            //add relation from sheet to the picture data
            PackageRelationship pr = sheet.getPackagePart().addRelationship(ppn, TargetMode.INTERNAL, relType, null);
            //set background picture to sheet
            sheet.getCTWorksheet().addNewPicture().setId(pr.getId());
        }
        OutputStream outputStream = new ByteArrayOutputStream();

        workbook.write(outputStream);
        return outputStream;
    }

    public String readExcelImageHiddenInfo(InputStream inputStream) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFPictureData xssfPictureData = workbook.getAllPictures().get(0);
        byte[] data = xssfPictureData.getData();
        return null;
    }

}
