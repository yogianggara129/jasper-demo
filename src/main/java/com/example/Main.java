package com.example;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.Invoice;
import com.example.models.Item;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Main {
    public static void main(String[] args) throws Exception {
        Main.generateInvoiceReport();
    }

    public static void generateInvoiceReport() throws Exception {
        // --- Data Dummy ---
        List<Invoice> invoices = List.of(
                new Invoice("INV-001", "Yogi", List.of(
                        new Item("Laptop", 1, 10000),
                        new Item("Mouse", 2, 200))),
                new Invoice("INV-002", "Budi", List.of(
                        new Item("Keyboard", 1, 300))));

        // --- Load JRXML dari resources ---
        InputStream reportStream = Main.class.getResourceAsStream("/reports/invoice_report.jrxml");
        if (reportStream == null) {
            throw new RuntimeException("Template tidak ditemukan!");
        }

        // --- Compile & Fill Report ---
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoices);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        // --- Export ke PDF ---
        JasperExportManager.exportReportToPdfFile(jasperPrint, "invoice_report.pdf");

        System.out.println("✅ Laporan berhasil dibuat: invoice_report.pdf");
    }

    public static void generateSample() throws JRException {
        // --- Dummy data ---
        List<Map<String, Object>> data = new ArrayList<>();
        data.add(Map.of("name", "Yogi", "email", "yogi@example.com"));
        data.add(Map.of("name", "Budi", "email", "budi@example.com"));

        // --- Load file jrxml dari resources ---
        InputStream reportStream = Main.class.getResourceAsStream("/reports/sample.jrxml");
        if (reportStream == null) {
            throw new RuntimeException("Template tidak ditemukan!");
        }

        // --- Compile dan isi report ---
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

        // ⚠️ Ganti dari Map.of(...) ke HashMap
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Daftar Pengguna");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        // --- Export ke file PDF ---
        JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");

        System.out.println("✅ Report berhasil dibuat: report.pdf");
    }

}