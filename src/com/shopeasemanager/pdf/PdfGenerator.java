package com.shopeasemanager.pdf;

import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.shopeasemanager.entity.Bill;
import com.shopeasemanager.entity.BillDetails;
import com.shopeasemanager.entity.Freebies;
import com.shopeasemanager.entity.Product;
import com.shopeasemanager.service.BillServiceImpl;
import com.shopeasemanager.service.FreebiesServiceImpl;
import com.shopeasemanager.service.ProductServiceImpl;

public class PdfGenerator {

    public static void generatePdf(List<BillDetails> billDetailsList) {
        BillServiceImpl billServiceImpl = new BillServiceImpl();
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        Long BillNo = billDetailsList.get(0).getBill().getBillNo();
        Bill bill = billServiceImpl.getBillFromBillNo(BillNo);

        PDDocument billDocument;
        String title = "COSMIC MARKET";
        billDocument = new PDDocument();
        PDPage newpage = new PDPage();
        billDocument.addPage(newpage);

        PDPage myPage = billDocument.getPage(0);
        try {
            PDPageContentStream cs = new PDPageContentStream(billDocument, myPage);

            float titleFontSize = 20;
            float titleWidth = titleFontSize * PDType1Font.TIMES_ROMAN.getStringWidth(title) / 1000;
            float titleXPosition = (myPage.getMediaBox().getWidth() - titleWidth) / 2;

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, titleFontSize);
            cs.newLineAtOffset(titleXPosition, 750);
            cs.showText(title);
            cs.endText();

            // Subtitle
            String subTitle = "Billing info";
            float subTitleFontSize = 18;
            float subTitleWidth = subTitleFontSize * PDType1Font.TIMES_ROMAN.getStringWidth(subTitle) / 1000;
            float subTitleXPosition = (myPage.getMediaBox().getWidth() - subTitleWidth) / 2;

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, subTitleFontSize);
            cs.newLineAtOffset(subTitleXPosition, 690);
            cs.showText(subTitle);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(30, 610);
            cs.showText("Bill No:");
            cs.newLine();
            cs.showText("Date of Purchase:");
            cs.newLine();
            cs.showText("Customer ID");
            cs.newLine();
            cs.showText("Customer Name");
            cs.newLine();
            cs.showText("Employee Name");
            cs.newLine();
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(160, 610);
            cs.showText(bill.getBillNo().toString());
            cs.newLine();
            cs.showText(bill.getDateOfPurchase().toString());
            cs.newLine();
            cs.showText(bill.getCustomer().getCustomerID());
            cs.newLine();
            cs.showText(bill.getCustomer().getCustomerFirstName() + " " + bill.getCustomer().getCustomerLastName());
            cs.newLine();
            cs.showText(bill.getEmployee().getEmployeeFirstName() + " " + bill.getEmployee().getEmployeeLastName());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(30, 350);
            cs.showText("Product");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(120, 350);
            cs.showText("Rate");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(170, 350);
            cs.showText("GST Rate");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(240, 350);
            cs.showText("Quantity");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(310, 350);
            cs.showText("Discount");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(400, 350);
            cs.showText("Credit Earned");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(500, 350);
            cs.showText("Price");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(30, 320);
            for (BillDetails billDetails : billDetailsList) {
                Product product = productServiceImpl.getProduct(billDetails.getProduct().getProductID());
                cs.showText(product.getProductName());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(120, 320);
            for (BillDetails billDetails : billDetailsList) {
                Product product = productServiceImpl.getProduct(billDetails.getProduct().getProductID());
                cs.showText(product.getProductRate().toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(170, 320);
            for (BillDetails billDetails : billDetailsList) {
                Product product = productServiceImpl.getProduct(billDetails.getProduct().getProductID());
                cs.showText(product.getProductGst().toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(240, 320);
            for (BillDetails billDetails : billDetailsList) {
                cs.showText(billDetails.getQuantity().toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(310, 320);
            for (BillDetails billDetails : billDetailsList) {
                cs.showText(billDetails.getDiscountEarned().toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(400, 320);
            for (BillDetails billDetails : billDetailsList) {
                cs.showText(billDetails.getCreditEarned().toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(500, 320);
            for (BillDetails billDetails : billDetailsList) {
                Product product = productServiceImpl.getProduct(billDetails.getProduct().getProductID());
                double totalRate = ((product.getProductRate() + product.getProductGst()) * billDetails.getQuantity())
                        - billDetails.getDiscountEarned();
                String total = String.valueOf(totalRate);
                cs.showText(total);
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(400, (320 - (20 * billDetailsList.size()) - 20));
            cs.showText("Total Discount:");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(400, (320 - (20 * billDetailsList.size()) - 40));
            cs.showText("Credit Earned:");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(400, (320 - (20 * billDetailsList.size()) - 60));
            cs.showText("Credit Used:");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(400, (320 - (20 * billDetailsList.size()) - 80));
            cs.showText("Total Rate:");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(500, (320 - (20 * billDetailsList.size()) - 20));
            cs.showText(bill.getDiscountApplied().toString());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(500, (320 - (20 * billDetailsList.size()) - 40));
            cs.showText(bill.getCreditEarned().toString());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(500, (320 - (20 * billDetailsList.size()) - 60));
            cs.showText(bill.getCreditUsed().toString());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(500, (320 - (20 * billDetailsList.size()) - 80));
            cs.showText(bill.getTotalRate().toString());
            cs.endText();

            FreebiesServiceImpl freebiesServiceImpl = new FreebiesServiceImpl();
            List<Freebies> freebiesList = freebiesServiceImpl.displayFreebies(BillNo);
            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(30, 200);
            cs.showText("Freebies:");
            cs.endText();

            // Print Freebies details
            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(30, 180);
            for (Freebies freebies : freebiesList) {
                Product freebieProduct = freebies.getProduct();
                cs.showText("Product: " + freebieProduct.getProductName() + ", Quantity: " + freebies.getQuantity());
                cs.newLine();
            }
            cs.endText();

            cs.close();
            String filePath = "P:\\SuperEaseManager\\Bill\\" + bill.getBillNo() + ".pdf";
            billDocument.save(filePath);
            System.out.println("BillPDF Generated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
