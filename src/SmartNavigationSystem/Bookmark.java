package SmartNavigationSystem;

import java.io.IOException;

public class Bookmark {
    
    private Mode mode;
    private Member member;

    public Bookmark(Mode mode, Member member) {
        this.mode = mode;
        this.member = member;
    }

    public Member getMember() {
        return this.member;
    }

    public Mode getMode() {
        return this.mode;
    }

    public static void addBookmark(String mode, Member member) throws IOException {
        JsonOperation.addNewBookMark(mode, member);
    }

    public static void deleteBookmark(Member member, int index) throws IOException {
        JsonOperation.deleteMemberBookmark(member, index);
    }

}
