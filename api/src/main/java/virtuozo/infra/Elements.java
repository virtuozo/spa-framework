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

package virtuozo.infra;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.BRElement;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DListElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.FieldSetElement;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.LegendElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.OListElement;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.dom.client.QuoteElement;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.TableCaptionElement;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.dom.client.UListElement;

public class Elements {

  public static Element byTagName(Element parent, String tagName) {
    NodeList<Element> nodeList = parent.getElementsByTagName(tagName);

    if (nodeList.getLength() > 0) {
      return nodeList.getItem(0);
    }

    return null;
  }

  public static NodeIterable<Element> allByTagName(Element parent, String tagName) {
    return NodeIterable.of(parent.getElementsByTagName(tagName));
  }

  public static Element childById(Element parent, String id) {
    NodeIterable<Node> elements = NodeIterable.of(parent.getChildNodes());

    for (Node node : elements) {
      Element child = (Element) node;
      if (child.getId().equals(id)) {
        return child;
      }
    }

    return null;
  }

  public static Element create(String tagName) {
    return document().createElement(tagName);
  }

  public static AnchorElement a() {
    return document().createAnchorElement();
  }

  public static Element abbr() {
    return create("abbr");
  }

  public static Element address() {
    return create("address");
  }

  public static QuoteElement blockquote() {
    return document().createBlockQuoteElement();
  }

  public static BodyElement body() {
    return document().getBody();
  }

  public static BRElement br() {
    return document().createBRElement();
  }

  public static ButtonElement button() {
    return document().createPushButtonElement();
  }

  public static TableCaptionElement caption() {
    return document().createCaptionElement();
  }

  public static InputElement checkbox() {
    return document().createCheckInputElement();
  }

  public static DivElement div() {
    return document().createDivElement();
  }

  public static Element dd() {
    return create("dd");
  }

  public static DListElement dl() {
    return document().createDLElement();
  }

  public static Element dt() {
    return create("dt");
  }

  public static Document document() {
    return Document.get();
  }

  public static Element em() {
    return create("em");
  }

  public static FieldSetElement fieldset() {
    return document().createFieldSetElement();
  }

  public static InputElement file() {
    return document().createFileInputElement();
  }

  public static FormElement form() {
    return document().createFormElement();
  }
  
  public static HeadingElement heading(int level) {
    return document().createHElement(level);
  }

  public static InputElement hidden() {
    return document().createHiddenInputElement();
  }

  public static ImageElement img() {
    return document().createImageElement();
  }

  public static LabelElement label() {
    return document().createLabelElement();
  }

  public static LegendElement legend() {
    return document().createLegendElement();
  }

  public static LIElement li() {
    return document().createLIElement();
  }

  public static OListElement ol() {
    return document().createOLElement();
  }

  public static OptionElement option() {
    return document().createOptionElement();
  }

  public static ParagraphElement p() {
    return document().createPElement();
  }

  public static InputElement password() {
    return document().createPasswordInputElement();
  }

  public static PreElement pre() {
    return document().createPreElement();
  }

  public static InputElement radio(String name) {
    return document().createRadioInputElement(name);
  }

  public static ButtonElement reset() {
    return document().createResetButtonElement();
  }

  public static ScriptElement script() {
    return document().createScriptElement();
  }

  public static SelectElement select(boolean multiple) {
    return document().createSelectElement(multiple);
  }

  public static Element small() {
    return create("small");
  }

  public static SpanElement span() {
    return document().createSpanElement();
  }

  public static Element strong() {
    return create("strong");
  }

  public static ButtonElement submit() {
    return document().createSubmitButtonElement();
  }

  public static TableElement table() {
    return document().createTableElement();
  }

  public static TableSectionElement tbody() {
    return document().createTBodyElement();
  }

  public static TableSectionElement thead() {
    return document().createTHeadElement();
  }

  public static TableSectionElement tfoot() {
    return document().createTFootElement();
  }

  public static TableCellElement th() {
    return document().createTHElement();
  }

  public static TableRowElement tr() {
    return document().createTRElement();
  }

  public static TableCellElement td() {
    return document().createTDElement();
  }

  public static InputElement text() {
    return document().createTextInputElement();
  }

  public static TextAreaElement textarea() {
    return document().createTextAreaElement();
  }

  public static UListElement ul() {
    return document().createULElement();
  }
}