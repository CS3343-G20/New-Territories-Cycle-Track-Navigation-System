package SmartNavigationSystem;

public class Bookmark implements BookmarkManager {

    private static Bookmark instance = new Bookmark();

    private Bookmark() {
    }

    public static Bookmark getInstance() {
        return instance;
    }

    public void addBookmark(String route, Member member) {
        JsonOperation.addNewBookMark(route, member);
    }

    public void deleteBookmark(Member member, int index) {
        JsonOperation.deleteMemberBookmark(member, index);
    }
}