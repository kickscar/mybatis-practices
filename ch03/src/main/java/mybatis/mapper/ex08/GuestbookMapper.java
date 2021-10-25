package mybatis.mapper.ex08;

import domain.Guestbook;

public interface GuestbookMapper {
    int insert(Guestbook guestbook);
    Guestbook findByNo01(Long no);
    Guestbook findByNo02(Long no);
}
