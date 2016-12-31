package demo.domain.user;

public class Permissions {
    private String roleNo;

    private String permissionsNo;

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo == null ? null : roleNo.trim();
    }

    public String getPermissionsNo() {
        return permissionsNo;
    }

    public void setPermissionsNo(String permissionsNo) {
        this.permissionsNo = permissionsNo == null ? null : permissionsNo.trim();
    }
}