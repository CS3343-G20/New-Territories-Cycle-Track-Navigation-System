package SmartNavigationSystem;

public class Bookmark implements BookmarkManager {

    private static Bookmark instance = new Bookmark();

    private Bookmark() {
    }

    public static Bookmark getInstance() {
        return instance;
    }

    @Override
    public void addBookmark(String route, Member member) {
        JsonOperation.addNewBookmark(route, member);
    }

    public void deleteBookmark(Member member, int index) throws ExInvalidIndex {
        JsonOperation.deleteMemberBookmark(member, index);
    }
}