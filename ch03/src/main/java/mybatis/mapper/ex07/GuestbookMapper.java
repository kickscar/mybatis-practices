package mybatis.mapper.ex07;

import domain.Guestbook;

public interface GuestbookMapper {
    int insert(Guestbook guestbook);
    Guestbook findAll();
    Guestbook findByNo(Long no);
}
