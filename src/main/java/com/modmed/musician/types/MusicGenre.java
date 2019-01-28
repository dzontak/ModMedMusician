package com.modmed.musician.types;

public enum MusicGenre {
  JAZZ("Jazz"),
  CLASSICAL("Classical"),
  ROCK("Rock"),
  UNKNOWN("Unknown");

  private final String code;

  MusicGenre(String code) {
    this.code = code;
  }

  public static MusicGenre fromCode(String code) {
    for (MusicGenre e : MusicGenre.values()) {
      if (code.equalsIgnoreCase(e.getCode())) {
        return e;
      }
    }
    return UNKNOWN;
  }

  public String getCode() {
    return code;
  }
}
