package pisi.unitedmeows.violentcat.holders;

public class ApplicationInfo {

    private int applicationId, applicationFlags;

    public ApplicationInfo(int _applicationId, int _applicationFlags) {
        applicationId = _applicationId;
        applicationFlags = _applicationFlags;
    }

    public int getApplicationFlags() {
        return applicationFlags;
    }

    public int getApplicationId() {
        return applicationId;
    }
}
