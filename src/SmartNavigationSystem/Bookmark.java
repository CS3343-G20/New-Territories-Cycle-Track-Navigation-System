package SmartNavigationSystem;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Bookmark {
    
    private Mode mode;
    private Member member;
    private int index;

    public Bookmark(Mode mode, Member member) {
        this.mode = mode;
        this.member = member;
        this.index = 0;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Member getMember() {
        return this.member;
    }

    public Mode getMode() {
        return this.mode;
    }

    public static void addBookmark(Mode mode, Member member) throws IOException {
        JsonOperation.addNewBookMark(new Bookmark(mode, member));
    }

    public static void deleteMemberBookmark(Member member, int index) throws IOException {
        JsonOperation.deleteMemberBookmark(member, index);
    }

}
