package mybatis.mapper.ex05;

import domain.Guestbook;

public interface GuestbookMapper {
    int insert(Guestbook guestbook);
    int update(Guestbook guestbook);
}
