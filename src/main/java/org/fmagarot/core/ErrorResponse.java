package org.fmagarot.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private List<String> reasons;
  private Locale locale;
}
