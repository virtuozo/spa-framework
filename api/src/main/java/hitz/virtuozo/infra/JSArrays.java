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
package hitz.virtuozo.infra;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

/**
 * Static helper functions for javascript arrays.
 */
public class JSArrays {

  private JSArrays() {
  }

  /**
   * Returns index of an item for an Array
   * 
   * @param o the item to search for in the array
   * @param a2 the array sopused to containing the element
   * @return -1 if object not found in array or int number for object indext in array starting from
   *         zero (0).
   */
  public static final native <T extends JavaScriptObject> int indexOf(JavaScriptObject o, JsArray<T> a) /*-{
		for (var i = 0, j = a.length; i < j; i++) {
			if (a[i] === o) {
				return i;
			}
		}
		return -1;
  }-*/;

  /**
   * Return the last object from the given array and remove it from the array. The resulting array
   * lenght is <code>a.length-1</code>.
   * 
   * @param a The array from which to pop an element
   * @return The element popped from the array.
   */
  public static final native <T extends JavaScriptObject> T pop(JsArray<T> a) /*-{
		return a.pop();
  }-*/;

  /**
   * Concatenates the given arrays and returns a new array containing the elements of both arrays.
   * The resulting array length is <code>a1.length+a2.length</code>.
   * 
   * @param a1 the first array to concatenate
   * @param a2 the second array to concatenate
   * @return A new array containing the elements from both arrays
   */
  public static final native <T extends JavaScriptObject> JsArray<T> concat(JsArray<T> a1, JsArray<T> a2) /*-{
		return a1.concat(a2);
  }-*/;

  /**
   * Reverses the sequence of the array's elements and returns the array. This method modifies the
   * original array.
   * 
   * @param a The array to reverse
   * @return The modified array.
   */
  public static final native <T extends JavaScriptObject> JsArray<T> reverse(JsArray<T> a) /*-{
		return a.reverse();
  }-*/;

  /**
   * Return a sub-array starting a the given index and ending with the last element in the given
   * array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   */
  public static final native <T extends JavaScriptObject> JsArray<T> slice(JsArray<T> a, int pos) /*-{
		return a.slice(pos);
  }-*/;

  /**
   * Return a sub-array starting a the given index and containing the given number of elements. If
   * the element count is negative, the index is relative to the length of the array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   * @param last If positive, the index of the element which follow the last element to extract. If
   *          negative, the position of the last element to extract counted from the last position
   *          in the array.
   */
  public static final native <T extends JavaScriptObject> JsArray<T> slice(JsArray<T> a, int pos, int last) /*-{
		return a.slice(pos, last);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position. The resulting array length
   * is <code>a.length-nrepl</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   */
  public static final native <T extends JavaScriptObject> void splice(JsArray<T> a, int pos, int nrepl) /*-{
		a.splice(pos, nrepl);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert an
   * object at the same position. The resulting array length is <code>a.length-nrepl+1</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o The object to insert.
   */
  public static final native <T extends JavaScriptObject> void splice(JsArray<T> a, int pos, int nrepl, JavaScriptObject o) /*-{
		a.splice(pos, nrepl, o);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert two
   * objects at the same position. The resulting array length is <code>a.length-nrepl+2</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first object to insert.
   * @param o2 The second object to insert.
   */
  public static final native <T extends JavaScriptObject> void splice(JsArray<T> a, int pos, int nrepl, JavaScriptObject o1, JavaScriptObject o2) /*-{
		a.splice(pos, nrepl, o1, o2);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert three
   * objects at the same position. The resulting array length is <code>a.length-nrepl+3</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first object to insert.
   * @param o2 The second object to insert.
   * @param o3 The third object to insert.
   */
  public static final native <T extends JavaScriptObject> void splice(JsArray<T> a, int pos, int nrepl, JavaScriptObject o1, JavaScriptObject o2, JavaScriptObject o3) /*-{
		a.splice(pos, nrepl, o1, o2, o3);
  }-*/;

  public static boolean isEmptyOrNull(JsArray<?> array) {
    return array == null || array.length() == 0;
  }

  /**
   * Insert an element at the given position. This is a shortcut for <code>splice(a,pos,0,o)</code>.
   * The resulting array length is <code>a.length+1</code>.
   * 
   * @param a The array to insert to.
   * @param pos The position of the element to insert.
   * @param o The object to insert.
   */
  public static final <T extends JavaScriptObject> void insert(JsArray<T> a, int pos, JavaScriptObject o) {
    splice(a, pos, 0, o);
  }

  /**
   * Remove an element from the given position. This is a shortcut for <code>splice(a,pos,1)</code>.
   * The resulting array length is <code>a.length-1</code>.
   * 
   * @param a The array to remove from.
   * @param pos The position of the element to remove.
   */
  public static final <T extends JavaScriptObject> void remove(JsArray<T> a, int pos) {
    splice(a, pos, 1);
  }

  /**
   * Sort an array using the given comparator function.
   * 
   * @param a The array to sort.
   * @param comp A javascript function, which take two arguments <code>a</code> and <code>b</code>
   *          of type <code>T</code> and returns a numeric value, which is less than zero, if
   *          <code>a&lt;b</code>, equal to zero, if <code>a==b</code> or greater than zero, if
   *          <code>a&gt;b</code>
   */
  public static final native <T extends JavaScriptObject> void sort(JsArray<T> a, JavaScriptObject comp) /*-{
		a.sort(comp);
  }-*/;

  /**
   * Return the first index in a sorted array for which <code>comp(a[i],needle)>=0</code> holds. The
   * returned position is the right place to insert <code>o</code> in order to keep the array
   * sorted.
   * 
   * @param a The sorted array to search in.
   * @param needle The object to search for.
   * @param comp A javascript function, which take two arguments <code>a</code> and <code>b</code>
   *          of type <code>T</code> and returns a numeric value, which is less than zero, if
   *          <code>a&lt;b</code>, equal to zero, if <code>a==b</code> or greater than zero, if
   *          <code>a&gt;b</code>.
   */
  public static final native <T extends JavaScriptObject> int lowerBound(JsArray<T> a, T needle, JavaScriptObject comp) /*-{
		var i0 = 0;
		var i1 = a.length;
		while (i0 < i1) {
			var i = i0 + Math.floor((i1 - i0) >> 1);
			var r = comp(a[i], needle);
			if (r < 0) {
				i0 = i + 1;
				continue;
			}
			if (r == 0){
				return i;
			}
			i1 = i;
		}
		return i0;
  }-*/;

  /**
   * Merge two sorted arrays.
   * 
   * @param a The first sorted array.
   * @param b The second sorted array.
   * @param allowDuplicates If <code>false</code>, it is assumed, that both input arrays contain
   *          only unique members and the output array will also contain only unique entries.
   * @param comp A javascript function, which take two arguments <code>a</code> and <code>b</code>
   *          of type <code>T</code> and returns a numeric value, which is less than zero, if
   *          <code>a&lt;b</code>, equal to zero, if <code>a==b</code> or greater than zero, if
   *          <code>a&gt;b</code>.
   * @return A sorted array, which contains the elements of both input arrays.
   */
  public static final native <T extends JavaScriptObject> JsArray<T> merge(JsArray<T> a, JsArray<T> b, boolean allowDuplicates, JavaScriptObject comp) /*-{

		var ret = [];
		var i = 0;
		var j = 0;
		while (i < a.length && j < b.length) {
			var r = comp(a[i], b[j]);
			if (r < 0) {
				ret.push(a[i]);
				++i;
				continue;
			}
			if (r == 0) {
				ret.push(a[i]);
				++i;
				if (allowDuplicates)
					ret.push(b[j]);
				++j;
				continue;
			}
			ret.push(b[j]);
			++j;
		}
		while (i < a.length) {
			ret.push(a[i]);
			++i;
		}
		while (j < b.length) {
			ret.push(b[j]);
			++j;
		}
		return ret;
  }-*/;

  /**
   * Append all elements of <code>b</code> to <code>a</code>. The resulting length of <code>a</code>
   * is <code>a.length+b.length</code>.
   * 
   * @param a The array to extend.
   * @param b The array to append to <code>a</code>.
   */
  public static final native <T extends JavaScriptObject> void pushAll(JsArray<T> a, JsArray<T> b) /*-{
		for (var i = 0; i < b.length; ++i)
			a.push(b[i]);
  }-*/;

  /**
   * Return the last object from the given array and remove it from the array. The resulting array
   * lenght is <code>a.length-1</code>.
   * 
   * @param a The array from which to pop an element
   * @return The element popped from the array.
   */
  public static final native String pop(JsArrayString a) /*-{
		return a.pop();
  }-*/;

  /**
   * Concatenates the given arrays and returns a new array containing the elements of both arrays.
   * The resulting array length is <code>a1.length+a2.length</code>.
   * 
   * @param a1 the first array to concatenate
   * @param a2 the second array to concatenate
   * @return A new array containing the elements from both arrays
   */
  public static final native JsArrayString concat(JsArrayString a1, JsArrayString a2) /*-{
		return a1.concat(a2);
  }-*/;

  /**
   * Reverses the sequence of the array's elements and returns the array. This method modifies the
   * original array.
   * 
   * @param a The array to reverse
   * @return The modified array.
   */
  public static final native JsArrayString reverse(JsArrayString a) /*-{
		return a.reverse();
  }-*/;

  /**
   * Return a sub-array starting a the given index and ending with the last element in the given
   * array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   */
  public static final native JsArrayString slice(JsArrayString a, int pos) /*-{
		return a.slice(pos);
  }-*/;

  /**
   * Return a sub-array starting a the given index and containing the given number of elements. If
   * the element count is negative, the index is relative to the length of the array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   * @param last If positive, the index of the element which follow the last element to extract. If
   *          negative, the position of the last element to extract counted from the last position
   *          in the array.
   */
  public static final native JsArrayString slice(JsArrayString a, int pos, int last) /*-{
		return a.slice(pos, last);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position. The resulting array length
   * is <code>a.length-nrepl</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   */
  public static final native void splice(JsArrayString a, int pos, int nrepl) /*-{
		a.splice(pos, nrepl);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert an
   * object at the same position. The resulting array length is <code>a.length-nrepl+1</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o The string to insert.
   */
  public static final native void splice(JsArrayString a, int pos, int nrepl, String o) /*-{
		a.splice(pos, nrepl, o);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert two
   * objects at the same position. The resulting array length is <code>a.length-nrepl+2</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first string to insert.
   * @param o2 The second string to insert.
   */
  public static final native void splice(JsArrayString a, int pos, int nrepl, String o1, String o2) /*-{
		a.splice(pos, nrepl, o1, o2);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert three
   * objects at the same position. The resulting array length is <code>a.length-nrepl+3</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first string to insert.
   * @param o2 The second string to insert.
   * @param o3 The third string to insert.
   */
  public static final native void splice(JsArrayString a, int pos, int nrepl, String o1, String o2, String o3) /*-{
		a.splice(pos, nrepl, o1, o2, o3);
  }-*/;

  /**
   * Insert an element at the given position. This is a shortcut for <code>splice(a,pos,0,o)</code>.
   * The resulting array length is <code>a.length+1</code>.
   * 
   * @param a The array to insert to.
   * @param pos The position of the element to insert.
   * @param o The string to insert.
   */
  public static final void insert(JsArrayString a, int pos, String o) {
    splice(a, pos, 0, o);
  }

  /**
   * Remove an element from the given position. This is a shortcut for <code>splice(a,pos,1)</code>.
   * The resulting array length is <code>a.length-1</code>.
   * 
   * @param a The array to remove from.
   * @param pos The position of the element to remove.
   */
  public static final void remove(JsArrayString a, int pos) {
    splice(a, pos, 1);
  }

  /**
   * Sort a string array lexicographically.
   * 
   * @param a The array to sort.
   * @param comp A javascript function, which take two arguments <code>a</code> and <code>b</code>
   *          of type <code>T</code> and returns a numeric value, which is less than zero, if
   *          <code>a&lt;b</code>, equal to zero, if <code>a==b</code> or greater than zero, if
   *          <code>a&gt;b</code>
   */
  public static final native void sort(JsArrayString a) /*-{
		a.sort();
  }-*/;

  /**
   * Sort an array using the given comparator function.
   * 
   * @param a The array to sort.
   * @param comp A javascript function, which take two arguments <code>a</code> and <code>b</code>
   *          of type <code>T</code> and returns a numeric value, which is less than zero, if
   *          <code>a&lt;b</code>, equal to zero, if <code>a==b</code> or greater than zero, if
   *          <code>a&gt;b</code>
   */
  public static final native void sort(JsArrayString a, JavaScriptObject comp) /*-{
		a.sort(comp);
  }-*/;

  /**
   * Return the first index in a lexicographically sorted array for which
   * <code>a[i] >= needle</code> holds. The returned position is the right place to insert
   * <code>o</code> in order to keep the array sorted.
   * 
   * @param a The sorted array to search in.
   * @param needle The string to search for.
   */
  public static final native int lowerBound(JsArrayString a, String needle) /*-{
		var i0 = 0;
		var i1 = a.length;
		while (i0 < i1) {
			var i = i0 + Math.floor((i1 - i0) >> 1);
			if (a[i] < needle){
				i0 = i + 1;
				continue;
			}
			if (a[i] == needle){
				return i;
			}
			i1 = i;
		}
		return i0;
  }-*/;

  /**
   * Return the first index in a sorted string array for which <code>comp(a[i],needle)>=0</code>
   * holds. The returned position is the right place to insert <code>o</code> in order to keep the
   * array sorted.
   * 
   * @param a The sorted array to search in.
   * @param needle The string to search for.
   * @param comp A javascript function, which take two arguments <code>a</code> and <code>b</code>
   *          of type <code>T</code> and returns a numeric value, which is less than zero, if
   *          <code>a&lt;b</code>, equal to zero, if <code>a==b</code> or greater than zero, if
   *          <code>a&gt;b</code>.
   */
  public static final native int lowerBound(JsArrayString a, String needle, JavaScriptObject comp) /*-{
		var i0 = 0;
		var i1 = a.length;
		while (i0 < i1) {
			var i = i0 + Math.floor((i1 - i0) >> 1);
			var r = comp(a[i], needle);
			if (r < 0){
				i0 = i + 1;
				continue;
			}
			if (r == 0){
				return i;
			}
			i1 = i;
		}
		return i0;
  }-*/;

  /**
   * Merge two lexicographically sorted arrays.
   * 
   * @param a The first sorted array.
   * @param b The second sorted array.
   * @param allowDuplicates If <code>false</code>, it is assumed, that both input arrays contain
   *          only unique members and the output array will also contain only unique entries.
   * @return A sorted array, which contains the elements of both input arrays.
   */
  public static final native JsArrayString merge(JsArrayString a, JsArrayString b, boolean allowDuplicates) /*-{

		var ret = [];
		var i = 0;
		var j = 0;
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				ret.push(a[i]);
				++i;
				continue;
			} 
			if (a[i] == b[j]) {
				ret.push(a[i]);
				++i;
				if (allowDuplicates)
					ret.push(b[j]);
				++j;
				continue;
			}
			ret.push(b[j]);
			++j;
		}
		while (i < a.length) {
			ret.push(a[i]);
			++i;
		}
		while (j < b.length) {
			ret.push(b[j]);
			++j;
		}
		return ret;
  }-*/;

  /**
   * Merge two sorted arrays.
   * 
   * @param a The first sorted array.
   * @param b The second sorted array.
   * @param allowDuplicates If <code>false</code>, it is assumed, that both input arrays contain
   *          only unique members and the output array will also contain only unique entries.
   * @param comp A javascript function, which take two arguments <code>a</code> and <code>b</code>
   *          of type <code>T</code> and returns a numeric value, which is less than zero, if
   *          <code>a&lt;b</code>, equal to zero, if <code>a==b</code> or greater than zero, if
   *          <code>a&gt;b</code>.
   * @return A sorted array, which contains the elements of both input arrays.
   */
  public static final native JsArrayString merge(JsArrayString a, JsArrayString b, boolean allowDuplicates, JavaScriptObject comp) /*-{

		var ret = [];
		var i = 0;
		var j = 0;
		while (i < a.length && j < b.length) {
			var r = comp(a[i], b[j]);
			if (r < 0) {
				ret.push(a[i]);
				++i;
				continue;
			} 
      if (r == 0) {
				ret.push(a[i]);
				++i;
				if (allowDuplicates)
					ret.push(b[j]);
				++j;
				continue;
			} 
			ret.push(b[j]);
			++j;
		}
		while (i < a.length) {
			ret.push(a[i]);
			++i;
		}
		while (j < b.length) {
			ret.push(b[j]);
			++j;
		}
		return ret;
  }-*/;

  /**
   * Append all elements of <code>b</code> to <code>a</code>. The resulting length of <code>a</code>
   * is <code>a.length+b.length</code>.
   * 
   * @param a The array to extend.
   * @param b The array to append to <code>a</code>.
   */
  public static final native void pushAll(JsArrayString a, JsArrayString b) /*-{
		for (var i = 0; i < b.length; ++i)
			a.push(b[i]);
  }-*/;

  /**
   * Return the last object from the given array and remove it from the array. The resulting array
   * lenght is <code>a.length-1</code>.
   * 
   * @param a The array from which to pop an element
   * @return The element popped from the array.
   */
  public static final native int pop(JsArrayInteger a) /*-{
		return a.pop();
  }-*/;

  /**
   * Concatenates the given arrays and returns a new array containing the elements of both arrays.
   * The resulting array length is <code>a1.length+a2.length</code>.
   * 
   * @param a1 the first array to concatenate
   * @param a2 the second array to concatenate
   * @return A new array containing the elements from both arrays
   */
  public static final native JsArrayInteger concat(JsArrayInteger a1, JsArrayInteger a2) /*-{
		return a1.concat(a2);
  }-*/;

  /**
   * Reverses the sequence of the array's elements and returns the array. This method modifies the
   * original array.
   * 
   * @param a The array to reverse
   * @return The modified array.
   */
  public static final native JsArrayInteger reverse(JsArrayInteger a) /*-{
		return a.reverse();
  }-*/;

  /**
   * Return a sub-array starting a the given index and ending with the last element in the given
   * array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   */
  public static final native JsArrayInteger slice(JsArrayInteger a, int pos) /*-{
		return a.slice(pos);
  }-*/;

  /**
   * Return a sub-array starting a the given index and containing the given number of elements. If
   * the element count is negative, the index is relative to the length of the array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   * @param last If positive, the index of the element which follow the last element to extract. If
   *          negative, the position of the last element to extract counted from the last position
   *          in the array.
   */
  public static final native JsArrayInteger slice(JsArrayInteger a, int pos, int last) /*-{
		return a.slice(pos, last);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position. The resulting array length
   * is <code>a.length-nrepl</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   */
  public static final native void splice(JsArrayInteger a, int pos, int nrepl) /*-{
		a.splice(pos, nrepl);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert an
   * object at the same position. The resulting array length is <code>a.length-nrepl+1</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o The integer to insert.
   */
  public static final native void splice(JsArrayInteger a, int pos, int nrepl, int o) /*-{
		a.splice(pos, nrepl, o);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert two
   * objects at the same position. The resulting array length is <code>a.length-nrepl+2</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first integer to insert.
   * @param o2 The second integer to insert.
   */
  public static final native void splice(JsArrayInteger a, int pos, int nrepl, int o1, int o2) /*-{
		a.splice(pos, nrepl, o1, o2);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert three
   * objects at the same position. The resulting array length is <code>a.length-nrepl+3</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first integer to insert.
   * @param o2 The second integer to insert.
   * @param o3 The third integer to insert.
   */
  public static final native void splice(JsArrayInteger a, int pos, int nrepl, int o1, int o2, int o3) /*-{
		a.splice(pos, nrepl, o1, o2, o3);
  }-*/;

  /**
   * Insert an element at the given position. This is a shortcut for <code>splice(a,pos,0,o)</code>.
   * The resulting array length is <code>a.length+1</code>.
   * 
   * @param a The array to insert to.
   * @param pos The position of the element to insert.
   * @param o The integer to insert.
   */
  public static final void insert(JsArrayInteger a, int pos, int o) {
    splice(a, pos, 0, o);
  }

  /**
   * Remove an element from the given position. This is a shortcut for <code>splice(a,pos,1)</code>.
   * The resulting array length is <code>a.length-1</code>.
   * 
   * @param a The array to remove from.
   * @param pos The position of the element to remove.
   */
  public static final void remove(JsArrayInteger a, int pos) {
    splice(a, pos, 1);
  }

  /**
   * Sort an integer array.
   * 
   * @param a The array to sort.
   */
  public static final native void sort(JsArrayInteger a) /*-{
		a.sort(function(x, y) {
			return x - y;
		});
  }-*/;

  /**
   * Return the first index in a sorted array for which <code>a[i] >= needle</code> holds. The
   * returned position is the right place to insert <code>o</code> in order to keep the array
   * sorted.
   * 
   * @param a The sorted array to search in.
   * @param needle The integer to search for.
   */
  public static final native int lowerBound(JsArrayInteger a, int needle) /*-{
		var i0 = 0;
		var i1 = a.length;
		while (i0 < i1) {
			var i = i0 + Math.floor((i1 - i0) >> 1);
			if (a[i] < needle){
				i0 = i + 1;
				continue;
			}
			if (a[i] == needle){
				return i;
			}
			i1 = i;
		}
		return i0;
  }-*/;

  /**
   * Merge two sorted arrays.
   * 
   * @param a The first sorted array.
   * @param b The second sorted array.
   * @param allowDuplicates If <code>false</code>, it is assumed, that both input arrays contain
   *          only unique members and the output array will also contain only unique entries.
   * @return A sorted array, which contains the elements of both input arrays.
   */
  public static final native JsArrayInteger merge(JsArrayInteger a, JsArrayInteger b, boolean allowDuplicates) /*-{

		var ret = [];
		var i = 0;
		var j = 0;
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				ret.push(a[i]);
				++i;
				continue;
			}
			if (a[i] == b[j]) {
				ret.push(a[i]);
				++i;
				if (allowDuplicates)
					ret.push(b[j]);
				++j;
				continue;
			}
			ret.push(b[j]);
			++j;
		}
		while (i < a.length) {
			ret.push(a[i]);
			++i;
		}
		while (j < b.length) {
			ret.push(b[j]);
			++j;
		}
		return ret;
  }-*/;

  /**
   * Append all elements of <code>b</code> to <code>a</code>. The resulting length of <code>a</code>
   * is <code>a.length+b.length</code>.
   * 
   * @param a The array to extend.
   * @param b The array to append to <code>a</code>.
   */
  public static final native void pushAll(JsArrayInteger a, JsArrayInteger b) /*-{
		for (var i = 0; i < b.length; ++i)
			a.push(b[i]);
  }-*/;

  /**
   * Return the last object from the given array and remove it from the array. The resulting array
   * lenght is <code>a.length-1</code>.
   * 
   * @param a The array from which to pop an element
   * @return The element popped from the array.
   */
  public static final native double pop(JsArrayNumber a) /*-{
		return a.pop();
  }-*/;

  /**
   * Concatenates the given arrays and returns a new array containing the elements of both arrays.
   * The resulting array length is <code>a1.length+a2.length</code>.
   * 
   * @param a1 the first array to concatenate
   * @param a2 the second array to concatenate
   * @return A new array containing the elements from both arrays
   */
  public static final native JsArrayNumber concat(JsArrayNumber a1, JsArrayNumber a2) /*-{
		return a1.concat(a2);
  }-*/;

  /**
   * Reverses the sequence of the array's elements and returns the array. This method modifies the
   * original array.
   * 
   * @param a The array to reverse
   * @return The modified array.
   */
  public static final native JsArrayNumber reverse(JsArrayNumber a) /*-{
		return a.reverse();
  }-*/;

  /**
   * Return a sub-array starting a the given index and ending with the last element in the given
   * array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   */
  public static final native JsArrayNumber slice(JsArrayNumber a, int pos) /*-{
		return a.slice(pos);
  }-*/;

  /**
   * Return a sub-array starting a the given index and containing the given number of elements. If
   * the element count is negative, the index is relative to the length of the array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   * @param last If positive, the index of the element which follow the last element to extract. If
   *          negative, the position of the last element to extract counted from the last position
   *          in the array.
   */
  public static final native JsArrayNumber slice(JsArrayNumber a, int pos, int last) /*-{
		return a.slice(pos, last);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position. The resulting array length
   * is <code>a.length-nrepl</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   */
  public static final native void splice(JsArrayNumber a, int pos, int nrepl) /*-{
		a.splice(pos, nrepl);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert an
   * object at the same position. The resulting array length is <code>a.length-nrepl+1</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o The number to insert.
   */
  public static final native void splice(JsArrayNumber a, int pos, int nrepl, double o) /*-{
		a.splice(pos, nrepl, o);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert two
   * objects at the same position. The resulting array length is <code>a.length-nrepl+2</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first number to insert.
   * @param o2 The second number to insert.
   */
  public static final native void splice(JsArrayNumber a, int pos, int nrepl, double o1, double o2) /*-{
		a.splice(pos, nrepl, o1, o2);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert three
   * objects at the same position. The resulting array length is <code>a.length-nrepl+3</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first number to insert.
   * @param o2 The second number to insert.
   * @param o3 The third number to insert.
   */
  public static final native void splice(JsArrayNumber a, int pos, int nrepl, double o1, double o2, double o3) /*-{
		a.splice(pos, nrepl, o1, o2, o3);
  }-*/;

  /**
   * Insert an element at the given position. This is a shortcut for <code>splice(a,pos,0,o)</code>.
   * The resulting array length is <code>a.length+1</code>.
   * 
   * @param a The array to insert to.
   * @param pos The position of the element to insert.
   * @param o The number to insert.
   */
  public static final void insert(JsArrayNumber a, int pos, double o) {
    splice(a, pos, 0, o);
  }

  /**
   * Remove an element from the given position. This is a shortcut for <code>splice(a,pos,1)</code>.
   * The resulting array length is <code>a.length-1</code>.
   * 
   * @param a The array to remove from.
   * @param pos The position of the element to remove.
   */
  public static final void remove(JsArrayNumber a, int pos) {
    splice(a, pos, 1);
  }

  /**
   * Sort a number array.
   * 
   * @param a The array to sort.
   * @param comp A javascript function, which take two arguments <code>a</code> and <code>b</code>
   *          of type <code>T</code> and returns a numeric value, which is less than zero, if
   *          <code>a&lt;b</code>, equal to zero, if <code>a==b</code> or greater than zero, if
   *          <code>a&gt;b</code>
   */
  public static final native void sort(JsArrayNumber a) /*-{
		a.sort(function(x, y) {
			return x - y;
		});
  }-*/;

  /**
   * Return the first index in a sorted array for which <code>a[i] >= needle</code> holds. The
   * returned position is the right place to insert <code>o</code> in order to keep the array
   * sorted.
   * 
   * @param a The sorted array to search in.
   * @param needle The number to search for.
   */
  public static final native int lowerBound(JsArrayNumber a, double needle) /*-{
		var i0 = 0;
		var i1 = a.length;
		while (i0 < i1) {
			var i = i0 + Math.floor((i1 - i0) >> 1);
			if (a[i] < needle){
				i0 = i + 1;
				continue;
			}
			if (a[i] == needle){
				return i;
			}
			i1 = i;
		}
		return i0;
  }-*/;

  /**
   * Merge two sorted arrays.
   * 
   * @param a The first sorted array.
   * @param b The second sorted array.
   * @param allowDuplicates If <code>false</code>, it is assumed, that both input arrays contain
   *          only unique members and the output array will also contain only unique entries.
   * @return A sorted array, which contains the elements of both input arrays.
   */
  public static final native JsArrayNumber merge(JsArrayNumber a, JsArrayNumber b, boolean allowDuplicates) /*-{

		var ret = [];
		var i = 0;
		var j = 0;
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				ret.push(a[i]);
				++i;
				continue;
			}
			if (a[i] == b[j]) {
				ret.push(a[i]);
				++i;
				if (allowDuplicates)
					ret.push(b[j]);
				++j;
				continue;
			}
			ret.push(b[j]);
			++j;
		}
		while (i < a.length) {
			ret.push(a[i]);
			++i;
		}
		while (j < b.length) {
			ret.push(b[j]);
			++j;
		}
		return ret;
  }-*/;

  /**
   * Append all elements of <code>b</code> to <code>a</code>. The resulting length of <code>a</code>
   * is <code>a.length+b.length</code>.
   * 
   * @param a The array to extend.
   * @param b The array to append to <code>a</code>.
   */
  public static final native void pushAll(JsArrayNumber a, JsArrayNumber b) /*-{
		for (var i = 0; i < b.length; ++i)
			a.push(b[i]);
  }-*/;

  /**
   * Return the last object from the given array and remove it from the array. The resulting array
   * lenght is <code>a.length-1</code>.
   * 
   * @param a The array from which to pop an element
   * @return The element popped from the array.
   */
  public static final native boolean pop(JsArrayBoolean a) /*-{
		return a.pop();
  }-*/;

  /**
   * Concatenates the given arrays and returns a new array containing the elements of both arrays.
   * The resulting array length is <code>a1.length+a2.length</code>.
   * 
   * @param a1 the first array to concatenate
   * @param a2 the second array to concatenate
   * @return A new array containing the elements from both arrays
   */
  public static final native JsArrayBoolean concat(JsArrayBoolean a1, JsArrayBoolean a2) /*-{
		return a1.concat(a2);
  }-*/;

  /**
   * Reverses the sequence of the array's elements and returns the array. This method modifies the
   * original array.
   * 
   * @param a The array to reverse
   * @return The modified array.
   */
  public static final native JsArrayBoolean reverse(JsArrayBoolean a) /*-{
		return a.reverse();
  }-*/;

  /**
   * Return a sub-array starting a the given index and ending with the last element in the given
   * array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   */
  public static final native JsArrayBoolean slice(JsArrayBoolean a, int pos) /*-{
		return a.slice(pos);
  }-*/;

  /**
   * Return a sub-array starting a the given index and containing the given number of elements. If
   * the element count is negative, the index is relative to the length of the array.
   * 
   * @param a The array to extract from.
   * @param pos The position of the first element to extract.
   * @param last If positive, the index of the element which follow the last element to extract. If
   *          negative, the position of the last element to extract counted from the last position
   *          in the array.
   */
  public static final native JsArrayBoolean slice(JsArrayBoolean a, int pos, int last) /*-{
		return a.slice(pos, last);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position. The resulting array length
   * is <code>a.length-nrepl</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   */
  public static final native void splice(JsArrayBoolean a, int pos, int nrepl) /*-{
		a.splice(pos, nrepl);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert an
   * object at the same position. The resulting array length is <code>a.length-nrepl+1</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o The boolean to insert.
   */
  public static final native void splice(JsArrayBoolean a, int pos, int nrepl, boolean o) /*-{
		a.splice(pos, nrepl, o);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert two
   * objects at the same position. The resulting array length is <code>a.length-nrepl+2</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first boolean to insert.
   * @param o2 The second boolean to insert.
   */
  public static final native void splice(JsArrayBoolean a, int pos, int nrepl, boolean o1, boolean o2) /*-{
		a.splice(pos, nrepl, o1, o2);
  }-*/;

  /**
   * Remove <code>nrepl</code> elements starting at the given position and afterwards insert three
   * objects at the same position. The resulting array length is <code>a.length-nrepl+3</code>.
   * 
   * @param a The array to remove elements from.
   * @param pos The position at which to remove elements.
   * @param nrepl The number of element to remove.
   * @param o1 The first boolean to insert.
   * @param o2 The second boolean to insert.
   * @param o3 The third boolean to insert.
   */
  public static final native void splice(JsArrayBoolean a, int pos, int nrepl, boolean o1, boolean o2, boolean o3) /*-{
		a.splice(pos, nrepl, o1, o2, o3);
  }-*/;

  /**
   * Insert an element at the given position. This is a shortcut for <code>splice(a,pos,0,o)</code>.
   * The resulting array length is <code>a.length+1</code>.
   * 
   * @param a The array to insert to.
   * @param pos The position of the element to insert.
   * @param o The boolean to insert.
   */
  public static final void insert(JsArrayBoolean a, int pos, boolean o) {
    splice(a, pos, 0, o);
  }

  /**
   * Remove an element from the given position. This is a shortcut for <code>splice(a,pos,1)</code>.
   * The resulting array length is <code>a.length-1</code>.
   * 
   * @param a The array to remove from.
   * @param pos The position of the element to remove.
   */
  public static final void remove(JsArrayBoolean a, int pos) {
    splice(a, pos, 1);
  }

  /**
   * Append all elements of <code>b</code> to <code>a</code>. The resulting length of <code>a</code>
   * is <code>a.length+b.length</code>.
   * 
   * @param a The array to extend.
   * @param b The array to append to <code>a</code>.
   */
  public static final native void pushAll(JsArrayBoolean a, JsArrayBoolean b) /*-{
		for (var i = 0; i < b.length; ++i)
			a.push(b[i]);
  }-*/;

  public static final native <H extends HashObject> void sort(JsArray<H> rows, String name, int direction)/*-{
		rows.sort(function(a, b) {
			if (direction > 0) {
				if (a[name] < b[name]) {
					return -1;
				}
				if (a[name] > b[name]) {
					return 1;
				}
				return 0;
			}

			if (b[name] < a[name]) {
				return -1;
			}

			if (b[name] > a[name]) {
				return 1;
			}

			return 0;
		});
  }-*/;

  public static final native <H extends HashObject> void sortNumber(JsArray<H> rows, String name, int direction)/*-{
		rows.sort(function(a, b) {
			if (direction > 0) {
				return a[name] - b[name];
			}

			return b[name] - a[name];
		});
  }-*/;

  public static final native <H extends HashObject> void sortBoolean(JsArray<H> rows, String name, int direction)/*-{
		rows.sort(function(a, b) {
			if (a[name] && b[name] || !a[name] && !b[name]) {
				return 0;
			}

			if (direction > 0) {
				if (!a[name] && b[name]) {
					return -1;
				}

				return 1;
			}

			if (!b[name] && a[name]) {
				return -1;
			}

			return 1;
		});
  }-*/;
}