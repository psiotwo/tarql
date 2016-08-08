package org.deri.tarql;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.sparql.core.Prologue;
import org.apache.jena.sparql.core.Var;

/**
 * A Tarql mapping. Conceptually, this is one or more SPARQL queries
 * with a shared prologue (prefixes and base declarations).
 */
public class TarqlQuery {
	public final static Var ROWNUM = Var.alloc("ROWNUM");
	
	private  Prologue prologue = null;
	private final List<Query> queries = new ArrayList<Query>();

	public TarqlQuery() {
		setPrologue(new Prologue());
	}
	
	public TarqlQuery(Query singleQuery) {
		setPrologue(singleQuery);
		addQuery(singleQuery);
	}
	
	public void addQuery(Query query) {
		queries.add(query);
	}
	
	public List<Query> getQueries() {
		return queries;
	}
	
	public void setPrologue(Prologue prologue) {
		this.prologue = prologue;
	}
	
	public Prologue getPrologue() {
		return prologue;
	}
	
	public boolean isConstructType() {
		return !queries.isEmpty() && queries.get(0).isConstructType();
	}
	
	public void makeTest() {
		for (Query q: queries) {
			if (q.isConstructType()) {
				q.setQuerySelectType();
			}
			q.setLimit(5);
		}
	}
}
