import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 */
public class ClassGenerator {

    private final Version version = Configuration.VERSION_2_3_29;
    private final Configuration config;

    public ClassGenerator() {
        config = new Configuration(version);
    }

    public static void main(String[] args) throws Exception {
        ClassGenerator classGenerator = new ClassGenerator();
        // Set Configuration
        classGenerator.config.setClassForTemplateLoading(ClassGenerator.class, "templates");
        classGenerator.config.setDefaultEncoding("UTF-8");
        classGenerator.config.setLocale(Locale.GERMANY);
        classGenerator.config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        for (int k = 0; k < 3; k++) {
            String path = "src/main/java/";
            String packageName = "generated.".concat("subPath_" + (k + 1));
            path = path.concat(packageName.replace(".", "/"));
            int maxClassesPerPackage = 3000;
            for (int i = 0; i < maxClassesPerPackage; i++) {
                Map<String, Object> input = new HashMap<>();
                List<ObjectMember> members = new ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    members.add(new ObjectMember("id" + (i + 1) + (j + 1), "int"));
                    members.add(new ObjectMember("name" + (i + 1) + (j + 1), "String"));
                    members.add(new ObjectMember("values" + (i + 1) + (j + 1), "List"));
                }
                String className = "DaoManager" + (k + 1);
                ClassObject daoManager = new ClassObject(packageName, className, members, (i + 1), maxClassesPerPackage);
                input.put("classObject", daoManager);

                Template template = classGenerator.config.getTemplate("ClassTemplate.ftl");

                File directory = new File(path);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File file = new File(path + "/" + className + (i + 1) + ".java");
                Writer fileWriter = new FileWriter(file);
                try {
                    template.process(input, fileWriter);
                } finally {
                    fileWriter.close();
                }
            }
        }
    }
}
