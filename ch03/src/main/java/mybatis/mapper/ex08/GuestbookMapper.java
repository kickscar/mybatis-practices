package mybatis.mapper.ex08;

import domain.Guestbook;

import java.util.Map;

public interface GuestbookMapper {
    int insert(Guestbook guestbook);
    Guestbook findByNo01(Long no);
    Guestbook findByNo02(Long no);
    Guestbook findByNo03(Long no);
    Map<String, Object> findByNo04(Long no);
}
