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
package virtuozo.interfaces;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

public class HTML {
  private static final Node body = new Node(Document.get().getBody());
  
  private static final Node head = new Node(Document.get().getElementsByTagName("head").getItem(0));
  
  private static final Map<String, Node> nodes = new HashMap<String, HTML.Node>();

  public static Node body() {
    return body;
  }
  
  public static Node head(){
    return head;
  }

  public static Node find(String id) {
    if(!nodes.containsKey(id)) {
      Element element = Document.get().getElementById(id);
      if (element == null) {
        throw new IllegalArgumentException("Element not found: id=" + id);
      }
      nodes.put(id, new Node(element));
    }
    
    return nodes.get(id);
  }
  
  public static class Node extends Composite<Node> {

    private Node(Element element) {
      super(element);
      this.attach();
      this.id(element.getId());
    }
  }
}
