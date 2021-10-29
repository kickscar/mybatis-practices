package mybatis.mapper.ex09;

import domain.Guestbook;

public interface GuestbookMapper {
    int insert(Guestbook guestbook);
    Guestbook findByNo(Long no);
}
