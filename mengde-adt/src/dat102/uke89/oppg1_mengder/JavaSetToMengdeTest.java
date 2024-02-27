package dat102.uke89.oppg1_mengder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaSetToMengdeTest {
	private MengdeADT<String> mengde;
	private MengdeADT<String> menge;
	private String s = "";
	private String st = "String";
	private String str = "Slobodan Drazic";
	
	private void leggTilElementer() {
		mengde.leggTil(s);
		mengde.leggTil(st);
		mengde.leggTil(str);
	}
	
	@BeforeEach
	void setup() {
		mengde = new JavaSetToMengde<>();
		menge = new JavaSetToMengde<>();
	}
	
	@Test
	void testConstructor() {
		assertEquals(0, mengde.antallElementer());
		assertTrue(mengde.erTom());
	}
	
	@Test
	void testLeggTilInneholder() {
		assertFalse(mengde.inneholder(s));
		assertFalse(mengde.inneholder(st));
		assertFalse(mengde.inneholder(str));
		
		leggTilElementer();
		mengde.leggTil(s);
		
		assertTrue(mengde.inneholder(s));
		assertTrue(mengde.inneholder(str));
		assertFalse(mengde.inneholder("S"));
		assertTrue(mengde.inneholder(st));
		assertEquals(3, mengde.antallElementer());
	}
	
	@Test
	void testErDelMengdeAvLik() {
		assertTrue(menge.erDelmengdeAv(mengde));
		assertTrue(menge.erLik(mengde));
		
		leggTilElementer();
		assertTrue(menge.erDelmengdeAv(mengde));
		assertFalse(menge.erLik(mengde));
		
		menge.leggTil(st);
		assertTrue(menge.erDelmengdeAv(mengde));
		assertFalse(menge.erLik(mengde));
		
		menge.leggTilAlleFra(mengde);
		assertTrue(menge.erDelmengdeAv(mengde));
		assertTrue(menge.erLik(mengde));
	}
	
	@Test
	void testDisjunkt() {
		assertTrue(mengde.erDisjunkt(menge));
		
		leggTilElementer();
		assertTrue(mengde.erDisjunkt(menge));
		
		menge.leggTil(st);
		assertFalse(mengde.erDisjunkt(menge));
		
		mengde.fjern(st);
		assertTrue(mengde.erDisjunkt(menge));
	}
	
	@Test
	void testSnitt() {
		MengdeADT<String> test = new JavaSetToMengde<>();
		assertTrue(test.erLik(mengde.snitt(menge)));
		
		leggTilElementer();
		assertTrue(test.erLik(mengde.snitt(menge)));
		
		menge.leggTil("S");
		menge.leggTil(st);
		test.leggTil(st);
		assertTrue(test.erLik(mengde.snitt(menge)));
	}
	
	@Test
	void testUnion() {
		MengdeADT<String> test = new JavaSetToMengde<>();
		assertTrue(test.erLik(mengde.union(menge)));
		
		leggTilElementer();
		test.leggTil(s);
		test.leggTil(str);
		test.leggTil(st);
		
		assertTrue(test.erLik(mengde.union(menge)));
		
		menge.leggTil(st);
		menge.leggTil(s);
		assertTrue(test.erLik(mengde.union(menge)));
		
		menge.leggTil("S");
		test.leggTil("S");
		assertTrue(test.erLik(mengde.union(menge)));
	}
	
	@Test
	void testMinus() {
		MengdeADT<String> test = new JavaSetToMengde<>();
		assertTrue(test.erLik(mengde.minus(menge)));
		
		leggTilElementer();
		assertFalse(test.erLik(mengde.minus(menge)));
		assertTrue(test.erLik(menge.minus(mengde)));
		
		test.leggTil(s);
		test.leggTil(st);
		test.leggTil(str);
		assertTrue(test.erLik(mengde.minus(menge)));
		assertFalse(test.erLik(menge.minus(mengde)));
		
		menge.leggTil(st);
		test.fjern(st);
		assertTrue(test.erLik(mengde.minus(menge)));
		assertFalse(test.erLik(menge.minus(mengde)));
		
		mengde.leggTil("S");
		menge.leggTil("Str");
		test.fjern(s);
		test.fjern(str);
		test.leggTil("Str");
		assertTrue(test.erLik(menge.minus(mengde)));
	}
	
	@Test
	void testTilTabell() {
		String[] test = {};
		assertArrayEquals(test, mengde.tilTabell());
		
		leggTilElementer();
		System.out.println(Arrays.toString(mengde.tilTabell()));
		String[] test1 = {s, str, st};
		assertArrayEquals(test1, mengde.tilTabell());
		
		mengde.fjern(st);
		String[] test2 = {s, str};
		assertArrayEquals(test2, mengde.tilTabell());
	}
}