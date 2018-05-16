import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class MyDeque<E>{

	private E[] data;
	private int start,end,size;

	@SuppressWarnings("unchecked")
	public MyDeque() {
		data = (E[])new Object[10];
	}

	@SuppressWarnings("unchecked")
	public MyDeque(int initialCapacity) {
		if (initialCapacity < 0){
			throw new IllegalArgumentException();
		}

		data = (E[]) new Object[initialCapacity];
	}

	public int size() {
		return size;
	}


	public void addFirst(E elem) {
		if (elem == null){
			throw new NullPointerException();
		}

		if (data[start] == null) {
			data[start] = elem;
		}

		else if (data.length == size()){
			resize();
			addFirst(elem);
		}

		else if (start == 0){
			start = data.length-1;
			data[start] = elem;
		}

		else{
			start--;
			data[start] = elem;
		}

		size++;

	}

	public void addLast(E elem) {
		if (elem == null){
			throw new NullPointerException();
		}

		if (data[end] == null) {
			data[end] = elem;
		}

		else if (data.length == size){
			resize();
			addLast(elem);
		}

		else if (end == data.length-1){
			end = 0;
			data[end] = elem;
		}

		else{
			data[++end] = elem;
		}

		size++;
	}

	public E removeFirst() {
		if(size == 0){
			throw new NoSuchElementException();
		}

		E rem = data[start];
		data[start] = null;

		if (start == data.length-1) {
			start = 0;
		}

		else {
			start++;
		}

		size--;
		return rem;
	}

	public E removeLast() {
		if(size == 0){
			throw new NoSuchElementException();
		}

		E rem = data[end];
		data[end] = null;

		if (end == 0) {
			end = data.length-1;
		}

		else {
			end--;
		}

		size--;
		return rem;
	}

	public E getFirst() {
		if(size == 0){
			throw new NoSuchElementException();
		}

		return data[start];

	}

	public E getLast() {
		if(size == 0){
			throw new NoSuchElementException();
		}

		return data[end];

	}


	@SuppressWarnings("unchecked")
	public void resize() {
		E[] resized = (E[]) new Object[data.length*2];
		int counter = 0;
		int start1 = start;

		while (counter<size) {

			if (start1 >= size){
				start1 = 0;
			}

			resized[counter] = data[start1];

			start1++;
			counter++;
		}

		start1 = start;
		end = size-1;
		data = resized;
	}
