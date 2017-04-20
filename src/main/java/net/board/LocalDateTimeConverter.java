package net.board;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// 바이너리 포맷을 타임스탬프로 바꾸는 클래스
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

  @Override
  public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
     return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
   }

  @Override
  public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
     return timestamp != null ? timestamp.toLocalDateTime() : null;
   }
}
