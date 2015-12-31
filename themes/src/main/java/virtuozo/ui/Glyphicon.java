/**
 * Copyright (C) 2004-2014 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package virtuozo.ui;

import virtuozo.ui.interfaces.Icon;
import virtuozo.ui.interfaces.UIComponent;

import com.google.gwt.dom.client.SpanElement;

public enum Glyphicon implements Icon {

  ASTERISK("glyphicon-asterisk"),
  PLUS("glyphicon-plus"),
  EURO("glyphicon-euro"),
  MINUS("glyphicon-minus"),
  CLOUD("glyphicon-cloud"),
  ENVELOPE("glyphicon-envelope"),
  PENCIL("glyphicon-pencil"),
  GLASS("glyphicon-glass"),
  MUSIC("glyphicon-music"),
  SEARCH("glyphicon-search"),
  HEART("glyphicon-heart"),
  STAR("glyphicon-star"),
  STAR_EMPTY("glyphicon-star-empty"),
  USER("glyphicon-user"),
  FILM("glyphicon-film"),
  TH_LARGE("glyphicon-th-large"),
  TH("glyphicon-th"),
  TH_LIST("glyphicon-th-list"),
  OK("glyphicon-ok"),
  REMOVE("glyphicon-remove"),
  ZOOM_IN("glyphicon-zoom-in"),
  ZOOM_OUT("glyphicon-zoom-out"),
  OFF("glyphicon-off"),
  SIGNAL("glyphicon-signal"),
  COG("glyphicon-cog"),
  TRASH("glyphicon-trash"),
  HOME("glyphicon-home"),
  FILE("glyphicon-file"),
  TIME("glyphicon-time"),
  ROAD("glyphicon-road"),
  DOWNLOAD_ALT("glyphicon-download-alt"),
  DOWNLOAD("glyphicon-download"),
  UPLOAD("glyphicon-upload"),
  INBOX("glyphicon-inbox"),
  PLAY_CIRCLE("glyphicon-play-circle"),
  REPEAT("glyphicon-repeat"),
  REFRESH("glyphicon-refresh"),
  LIST_ALT("glyphicon-list-alt"),
  LOCK("glyphicon-lock"),
  FLAG("glyphicon-flag"),
  HEADPHONES("glyphicon-headphones"),
  VOLUME_OFF("glyphicon-volume-off"),
  VOLUME_DOWN("glyphicon-volume-down"),
  VOLUME_UP("glyphicon-volume-up"),
  QRCODE("glyphicon-qrcode"),
  BARCODE("glyphicon-barcode"),
  TAG("glyphicon-tag"),
  TAGS("glyphicon-tags"),
  BOOK("glyphicon-book"),
  BOOKMARK("glyphicon-bookmark"),
  PRINT("glyphicon-print"),
  CAMERA("glyphicon-camera"),
  FONT("glyphicon-font"),
  BOLD("glyphicon-bold"),
  ITALIC("glyphicon-italic"),
  TEXT_HEIGHT("glyphicon-text-height"),
  TEXT_WIDTH("glyphicon-text-width"),
  ALIGN_LEFT("glyphicon-align-left"),
  ALIGN_CENTER("glyphicon-align-center"),
  ALIGN_RIGHT("glyphicon-align-right"),
  ALIGN_JUSTIFY("glyphicon-align-justify"),
  LIST("glyphicon-list"),
  INDENT_LEFT("glyphicon-indent-left"),
  INDENT_RIGHT("glyphicon-indent-right"),
  FACETIME_VIDEO("glyphicon-facetime-video"),
  PICTURE("glyphicon-picture"),
  MAP_MARKER("glyphicon-map-marker"),
  ADJUST("glyphicon-adjust"),
  TINT("glyphicon-tint"),
  EDIT("glyphicon-edit"),
  SHARE("glyphicon-share"),
  CHECK("glyphicon-check"),
  MOVE("glyphicon-move"),
  STEP_BACKWARD("glyphicon-step-backward"),
  FAST_BACKWARD("glyphicon-fast-backward"),
  BACKWARD("glyphicon-backward"),
  PLAY("glyphicon-play"),
  PAUSE("glyphicon-pause"),
  STOP("glyphicon-stop"),
  FORWARD("glyphicon-forward"),
  FAST_FORWARD("glyphicon-fast-forward"),
  STEP_FORWARD("glyphicon-step-forward"),
  EJECT("glyphicon-eject"),
  CHEVRON_LEFT("glyphicon-chevron-left"),
  CHEVRON_RIGHT("glyphicon-chevron-right"),
  PLUS_SIGN("glyphicon-plus-sign"),
  MINUS_SIGN("glyphicon-minus-sign"),
  REMOVE_SIGN("glyphicon-remove-sign"),
  OK_SIGN("glyphicon-ok-sign"),
  QUESTION_SIGN("glyphicon-question-sign"),
  INFO_SIGN("glyphicon-info-sign"),
  SCREENSHOT("glyphicon-screenshot"),
  REMOVE_CIRCLE("glyphicon-remove-circle"),
  OK_CIRCLE("glyphicon-ok-circle"),
  BAN_CIRCLE("glyphicon-ban-circle"),
  ARROW_LEFT("glyphicon-arrow-left"),
  ARROW_RIGHT("glyphicon-arrow-right"),
  ARROW_UP("glyphicon-arrow-up"),
  ARROW_DOWN("glyphicon-arrow-down"),
  SHARE_ALT("glyphicon-share-alt"),
  RESIZE_FULL("glyphicon-resize-full"),
  RESIZE_SMALL("glyphicon-resize-small"),
  EXCLAMATION_SIGN("glyphicon-exclamation-sign"),
  GIFT("glyphicon-gift"),
  LEAF("glyphicon-leaf"),
  FIRE("glyphicon-fire"),
  EYE_OPEN("glyphicon-eye-open"),
  EYE_CLOSE("glyphicon-eye-close"),
  WARNING_SIGN("glyphicon-warning-sign"),
  PLANE("glyphicon-plane"),
  CALENDAR("glyphicon-calendar"),
  RANDOM("glyphicon-random"),
  COMMENT("glyphicon-comment"),
  MAGNET("glyphicon-magnet"),
  CHEVRON_UP("glyphicon-chevron-up"),
  CHEVRON_DOWN("glyphicon-chevron-down"),
  RETWEET("glyphicon-retweet"),
  SHOPPING_CART("glyphicon-shopping-cart"),
  FOLDER_CLOSE("glyphicon-folder-close"),
  FOLDER_OPEN("glyphicon-folder-open"),
  RESIZE_VERTICAL("glyphicon-resize-vertical"),
  RESIZE_HORIZONTAL("glyphicon-resize-horizontal"),
  HDD("glyphicon-hdd"),
  BULLHORN("glyphicon-bullhorn"),
  BELL("glyphicon-bell"),
  CERTIFICATE("glyphicon-certificate"),
  THUMBS_UP("glyphicon-thumbs-up"),
  THUMBS_DOWN("glyphicon-thumbs-down"),
  HAND_RIGHT("glyphicon-hand-right"),
  HAND_LEFT("glyphicon-hand-left"),
  HAND_UP("glyphicon-hand-up"),
  HAND_DOWN("glyphicon-hand-down"),
  CIRCLE_ARROW_RIGHT("glyphicon-circle-arrow-right"),
  CIRCLE_ARROW_LEFT("glyphicon-circle-arrow-left"),
  CIRCLE_ARROW_UP("glyphicon-circle-arrow-up"),
  CIRCLE_ARROW_DOWN("glyphicon-circle-arrow-down"),
  GLOBE("glyphicon-globe"),
  WRENCH("glyphicon-wrench"),
  TASKS("glyphicon-tasks"),
  FILTER("glyphicon-filter"),
  BRIEFCASE("glyphicon-briefcase"),
  FULLSCREEN("glyphicon-fullscreen"),
  DASHBOARD("glyphicon-dashboard"),
  PAPERCLIP("glyphicon-paperclip"),
  HEART_EMPTY("glyphicon-heart-empty"),
  LINK("glyphicon-link"),
  PHONE("glyphicon-phone"),
  PUSHPIN("glyphicon-pushpin"),
  USD("glyphicon-usd"),
  GBP("glyphicon-gbp"),
  SORT("glyphicon-sort"),
  SORT_BY_ALPHABET("glyphicon-sort-by-alphabet"),
  SORT_BY_ALPHABET_ALT("glyphicon-sort-by-alphabet-alt"),
  SORT_BY_ORDER("glyphicon-sort-by-order"),
  SORT_BY_ORDER_ALT("glyphicon-sort-by-order-alt"),
  SORT_BY_ATTRIBUTES("glyphicon-sort-by-attributes"),
  SORT_BY_ATTRIBUTES_ALT("glyphicon-sort-by-attributes-alt"),
  UNCHECKED("glyphicon-unchecked"),
  EXPAND("glyphicon-expand"),
  COLLAPSE_DOWN("glyphicon-collapse-down"),
  COLLAPSE_UP("glyphicon-collapse-up"),
  LOG_IN("glyphicon-log-in"),
  FLASH("glyphicon-flash"),
  LOG_OUT("glyphicon-log-out"),
  NEW_WINDOW("glyphicon-new-window"),
  RECORD("glyphicon-record"),
  SAVE("glyphicon-save"),
  OPEN("glyphicon-open"),
  SAVED("glyphicon-saved"),
  IMPORT("glyphicon-import"),
  EXPORT("glyphicon-export"),
  SEND("glyphicon-send"),
  FLOPPY_DISK("glyphicon-floppy-disk"),
  FLOPPY_SAVED("glyphicon-floppy-saved"),
  FLOPPY_REMOVE("glyphicon-floppy-remove"),
  FLOPPY_SAVE("glyphicon-floppy-save"),
  FLOPPY_OPEN("glyphicon-floppy-open"),
  CREDIT_CARD("glyphicon-credit-card"),
  TRANSFER("glyphicon-transfer"),
  CUTLERY("glyphicon-cutlery"),
  HEADER("glyphicon-header"),
  COMPRESSED("glyphicon-compressed"),
  EARPHONE("glyphicon-earphone"),
  PHONE_ALT("glyphicon-phone-alt"),
  TOWER("glyphicon-tower"),
  STATS("glyphicon-stats"),
  SD_VIDEO("glyphicon-sd-video"),
  HD_VIDEO("glyphicon-hd-video"),
  SUBTITLES("glyphicon-subtitles"),
  SOUND_STEREO("glyphicon-sound-stereo"),
  SOUND_DOLBY("glyphicon-sound-dolby"),
  SOUND_5_1("glyphicon-sound-5-1"),
  SOUND_6_1("glyphicon-sound-6-1"),
  SOUND_7_1("glyphicon-sound-7-1"),
  COPYRIGHT_MARK("glyphicon-copyright-mark"),
  REGISTRATION_MARK("glyphicon-registration-mark"),
  CLOUD_DOWNLOAD("glyphicon-cloud-download"),
  CLOUD_UPLOAD("glyphicon-cloud-upload"),
  TREE_CONIFER("glyphicon-tree-conifer"),
  TREE_DECIDUOUS("glyphicon-tree-deciduous");

  private String name;
  
  private static final String prefix = "glyphicon";
  
  private Glyphicon(String name) {
    this.name = name;
  }
  
  public UIComponent asComponent(){
    Tag<SpanElement> icon = Tag.asSpan();
    this.update(icon);
    return icon;
  }
  
  @Override
  public boolean is(UIComponent component) {
    return component.asComponent().css().contains(prefix);
  }

  public void attachTo(UIComponent component) {
    Icons.attachTo(component, this);
  }
  
  @Override
  public void update(UIComponent component) {
    component.asComponent().css().set(prefix).append(this.name);
  }
}