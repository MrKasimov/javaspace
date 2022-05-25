package org.javaspace.integration

import org.javaspace.compiler.Compiler
import org.apache.commons.io.FileUtils
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Method

class ShouldCompileTest extends Specification {

	private final static helloWorld =
							"""
							HelloWorld {
								start {
									print "Hello world! It's JavaSpace!"
								}
							}
							"""

	private final static controlStatements =
							"""
							ControlStatements {
								start() {
									print "Print from 1 to 10"
									for x from 1 to 10 {
										if (x == 5)
											print x
									}
								}
							}
							"""

	private final static types =
							"""
							PrimitiveTypes {
								start() {
									var stringVar = "str"
									var booleanVar = false
									var integerVar = 2745
									var doubleVar = 2343.05
							
									print "stringVar=" + stringVar + ", booleanVar=" + booleanVar + ", integerVar=" + integerVar + ", doubleVar=" + doubleVar
									print 2.5+2.5 + " is the sum of 2.5 and 2.5"
								}
							}
							"""

	@Unroll
	def "Should Compile and run"() {
		expect:
			def file = new File(filename)
			FileUtils.writeStringToFile(file, code)
			Compiler.main(filename)

			URL u = new File(".").toURL();
			URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			Class urlClass = URLClassLoader.class;
			Method method = urlClass.getDeclaredMethod("addURL", URL.class);
			method.setAccessible(true);
			method.invoke(urlClassLoader, u) == null;

		where:
			code                     | filename
			helloWorld               | "HelloWorld.javaspace"
			controlStatements | "ControlStatements.javaspace"
			types | "PrimitiveTypes.javaspace"
	}
}