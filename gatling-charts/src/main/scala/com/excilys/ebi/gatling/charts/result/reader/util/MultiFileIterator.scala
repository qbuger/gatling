/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.excilys.ebi.gatling.charts.result.reader.util

import java.io.File

import scala.io.Source

class MultiFileIterator(files: Iterator[File], encoding: String) extends Iterator[String] {
	var currentIterator: Iterator[String] = Iterator.empty

	def hasNext: Boolean = {
		if (currentIterator.hasNext) true
		else {
			while (files.hasNext && !currentIterator.hasNext)
				currentIterator = Source.fromFile(files.next(), encoding).getLines()
			currentIterator.hasNext
		}
	}

	def next: String = currentIterator.next
}
