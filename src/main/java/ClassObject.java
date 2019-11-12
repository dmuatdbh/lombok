import java.util.List;

/**
 *
 */
public class ClassObject {

    private String packageName;
    private String className;
    private List<ObjectMember> members;
    private int classId;
    private int classCount;

    public ClassObject(String packageName, String className, List<ObjectMember> members, int classId, int classCount) {
        this.packageName = packageName;
        this.className = className;
        this.members = members;
        this.classId = classId;
        this.classCount = classCount;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public List<ObjectMember> getMembers() {
        return members;
    }

    public String getClassId() {
        return "" + classId;
    }

    public String getLinkedClassId() {
        return classId < classCount ? "" + (classId + 1) : "1";
    }
}
