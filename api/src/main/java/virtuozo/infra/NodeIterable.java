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

import java.util.Iterator;

import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;

public class NodeIterable<N extends Node> implements Iterable<N> {

  private Iterator<N> iterator;

  private NodeIterable(NodeList<N> list) {
    this.iterator = new NodeIterator<N>(list);
  }
  
  public static <N extends Node> NodeIterable<N> of(NodeList<N> list) {
    return new NodeIterable<N>(list);
  }

  @Override
  public Iterator<N> iterator() {
    return this.iterator;
  }

  @SuppressWarnings("hiding")
  class NodeIterator<N extends Node> implements Iterator<N> {

    private NodeList<N> list;

    private int index;

    public NodeIterator(NodeList<N> list) {
      super();
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      if (this.list == null) {
        return false;
      }

      return this.index < this.list.getLength();
    }

    @Override
    public N next() {
      return this.list.getItem(this.index++);
    }

    @Override
    public void remove() {
      return;
    }
  }
}