package ${classObject.packageName};

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class ${classObject.className}${classObject.classId} {

    <#list classObject.members as member>
        @Getter
        @Setter
        private ${member.type} ${member.name};

    </#list>

    public ${classObject.className}${classObject.classId}() {
    }

    public void testMethode() {
        int id = new ${classObject.className}${classObject.linkedClassId}().getId${classObject.linkedClassId}1();
    }
}