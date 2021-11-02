package SmartNavigationSystem;

import java.io.IOException;

public class Bookmark {
    
    private Member member;

    public Bookmark(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return this.member;
    }

    public static void addBookmark(String route, Member member) throws IOException {
        JsonOperation.addNewBookMark(route, member);
    }

    public static void deleteBookmark(Member member, int index) throws IOException {
        JsonOperation.deleteMemberBookmark(member, index);
    }

}
