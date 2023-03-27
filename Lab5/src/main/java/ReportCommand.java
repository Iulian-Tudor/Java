import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportCommand implements Command {
    private Catalog catalog;

    public ReportCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        List<Document> documents = catalog.getDocuments();

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassLoaderForTemplateLoading(ReportCommand.class.getClassLoader(), "/");
        cfg.setDefaultEncoding("UTF-8");

        try {
            Template template = cfg.getTemplate("report.ftl");

            Map<String, Object> templateData = new HashMap<>();
            templateData.put("documents", documents);
            //Inlocuieste datele in template
            StringWriter stringWriter = new StringWriter();
            template.process(templateData, stringWriter);
            String html = stringWriter.toString();

            // Creaza un fisier temporar pentru html
            File tempFile = File.createTempFile("report", ".html");
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write(html);
            fileWriter.close();

            // Deschide in browser html
            Desktop.getDesktop().browse(tempFile.toURI());
        } catch (IOException | TemplateException e) {
            System.out.println("Could not generate report: " + e.getMessage());
        }
    }
}
