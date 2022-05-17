/**
Le package TP contient deux classes. La classe Employe et la classe DeptEmp
Chacune de ces classes est dans son propre fichier.
La classe Employe contient les m�thodes suivantes :



public void insertOneEmploye(String nomCollection, Document employe);
public void testInsertOneEmploye();
public void insertManyEmployes(String nomCollection, List<Document> employes);
public void testInsertManyEmployes();
public void getEmployeById(String nomCollection, Integer empId);
public void getEmployes(String nomCollection, 
	Document whereQuery, 
	Document projectionFields,
	Document sortFields);
public void updateEmployes(String nomCollection, 
	Document whereQuery, 
	Document updateExpressions, UpdateOptions updateOptions);
public void deleteEmployes(String nomCollection, Document filters);
public void displayIterator(Iterator it, String message);   
public void joinLocalAndforeignCollections(
	String localCollectionName, 
	String ForeignCollectionName, 
	String localColJoinFieldName,
	String foreigColJoinFieldName,
	String filterFieldName,
	String filterFieldType,
	String filterFieldValue);
public void groupByOnOneCollection(String localCollectionName);
public void createEmployeIndexes(
		String localCollectionName,
		String indexName,
		String indexFieldName1,
		String indexFieldName2,
		String indexFieldName3,
		String indexType,
		boolean isAscendingIndex,
		boolean indexUnique);		
public void getAllIndexesOfACollection(String localCollectionName);
public void dropAIndexOfACollection(
		String localCollectionName,
		String indexName);

Chaque m�thode est d�crite lors de sa d�claration dans la classe.

*/

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import java.util.*;

import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.IndexOptions;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;

public class Employe { 
   private MongoDatabase database;
   private String dbName="RH";
   private String hostName="localhost";
   private int port=27017;
   private String userName="urh";
   private String passWord="passUrh";
   private String EmployeCollectionName="colEmployes";

   /**
   Le  
	
   */
   public static void main( String args[] ) {  
    try{
		Employe emp = new Employe();
		// TE1a : test des fonctions de gestion d'une collection et d'ajout de documents

		emp.dropCollectionEmploye(emp.EmployeCollectionName);
		emp.createCollectionEmploye(emp.EmployeCollectionName);
		emp.deleteEmployes(emp.EmployeCollectionName, new Document());
		emp.testInsertOneEmploye();
		emp.testInsertManyEmployes();
		emp.getEmployeById(emp.EmployeCollectionName, 4585);

		// TE1b: Afficher tous les employ�s sans tri ni projection
		System.out.println("\n\nTE1b : ");

		emp.getEmployes(emp.EmployeCollectionName, 
			new Document(), 
			new Document(),
			new Document());
		
		// TE2 : Afficher tous les employ�s salesman du d�partement 30
		// Tri� en ordre croissant sur _id
		// Projet� sur _id, name, job, deptno et adresse
		System.out.println("\n\nTE2 : ...");

		emp.getEmployes(emp.EmployeCollectionName, 
			new Document("job", "Salesman").append("deptno",30), 
			new Document("_id", 1).append("name",1).
			append("job",1).append("deptno", 1).
			append("adresse",1),
			new Document("_id", 1).append("name",1)
		);
		
		// TE3 : Augmenter les employ�s travaillant dans le d�partement de 	10%
		
		System.out.println("\n\nTE3 : ...");

		emp.updateEmployes(emp.EmployeCollectionName, 
		new Document("deptno", 30), 
		new Document ("$mul", new Document("sal", 1.1) ),
		new UpdateOptions());
		
		// TE4 : afficher les informations sur les employ�s du d�partement 10
		
		System.out.println("\n\nTE4 : ...");

		emp.joinLocalAndforeignCollections("colDepts","colEmployes", 
		"_id", "deptno", "_id", 10);

		// TE5: afficher les employ�s group�s par job
		System.out.println("\n\nTE5 : ...");

		emp.groupByOnOneCollectionByJob("colEmployes");

		// TE6: Cr�er un index nomm�
		// Cr�ation d'un index
		System.out.println("\n\nTE6 : ...");

		emp.createEmployeIndexes(
			emp.EmployeCollectionName,
			"idx_"+emp.EmployeCollectionName+"_name",
			"name",
			null,
			null,
			true,
			false
		);
		// TE7 : r�cup�rer les informations sur tous les indexes
		System.out.println("\n\nTE7 : ...");

		emp.getAllIndexesOfACollection(emp.EmployeCollectionName);

		// TE8 : supprimer un index
		System.out.println("\n\nTE8 : ...");

		emp.dropAIndexOfACollection(emp.EmployeCollectionName, "idx_colEmployes_name");

	}catch(Exception e){
		e.printStackTrace();
	}	
   } 
   
   /**
	FE1 : Constructeur Employe.
	Dans ce constructeur sont effectu�es les activit�s suivantes:
	- Cr�ation d'une instance du client MongoClient
	- Cr�ation d'une BD Mongo appel� RH
	- Cr�ation d'un utilisateur appel� 
	- Chargement du pointeur vers la base RH
   */
   
   Employe(){
		// Creating a Mongo client
		
		MongoClient mongoClient = new MongoClient( hostName , port ); 

		// Creating Credentials 
		// RH : Ressources Humaines
		MongoCredential credential; 
		credential = MongoCredential.createCredential(userName, dbName, 
		 passWord.toCharArray()); 
		System.out.println("Connected to the database successfully"); 	  
		System.out.println("Credentials ::"+ credential);  
		// Accessing the database 
		database = mongoClient.getDatabase(dbName); 

   }
   /**
	FE2 : Cette fonction permet de cr�er une collection
	de nom nomCollection.
	Travail � faire : compl�ter cette m�thode
   */
   public void createCollectionEmploye(String nomCollection){
	//Creating a collection 
	this.database.createCollection(nomCollection);
	System.out.println("Collection "+nomCollection+" created successfully");
   }
   
   
   /**
	FE3 : Cette fonction permet de supprimer une collection
	de nom nomCollection.
		Travail � faire : compl�ter cette m�thode

   */

   public void dropCollectionEmploye(String nomCollection) {
	   //Drop a collection
	   MongoCollection<Document> colEmployes = this.database.getCollection(nomCollection);
	   if (colEmployes == null)
		   System.out.println("Collection " + nomCollection + " inexistante");
	   else {
		   colEmployes.drop();
		   System.out.println("Collection " + nomCollection + " removed successfully !!!");
	   }
   }

   /**
	FE4 : Cette fonction permet d'ins�rer un employ� dans une collection.
	Travail � faire : compl�ter cette m�thode

   */
   
   public void insertOneEmploye(String nomCollection, Document employe){
		//Drop a collection 
	   MongoCollection<Document> colEmploye=database.getCollection(nomCollection);
	   colEmploye.insertOne(employe);
	   System.out.println("Document inserted successfully");
   }

   /**
	FE5 : Cette fonction permet de tester la m�thode insertOneEmploye.
	Travail � faire : compl�ter cette m�thode

   */

   public void testInsertOneEmploye(){
	Document employe = new Document("_id", 4585)
			.append("name", "CHOISY")
			.append("prenoms", Arrays.asList("Sebastien"))
			.append("adresse", new Document(
					"numero", 211)
					.append("rue", "chemin de la rue")
					.append("ville", "Valbonne")
					.append("codePostal", "06560")
					.append("Pays", "France"))
			.append("job", "Etudiant")
			.append("mgr", null)
			.append("hiredate", "17-11-1999")
			.append("sal", 5000)
			.append("comm", null)
			.append("deptno", 10);
	this.database.getCollection(this.EmployeCollectionName).insertOne(employe);
	System.out.println("Document inserted successfully");
   }

   /**
	FE6 : Cette fonction permet d'ins�rer plusieurs Employ�s dans une collection
	Travail � faire : compl�ter cette m�thode

	*/

   public void insertManyEmployes(String nomCollection, List<Document> employes){
	   MongoCollection<Document> colEmployes = database.getCollection(nomCollection);
	   colEmployes.insertMany(employes);
	   System.out.println("Many Documents inserted successfully");
   }

   /**
	FE7 : Cette fonction permet de tester la fonction insertManyEmployes
   */

   public void testInsertManyEmployes(){
		List<Document> employes = Arrays.asList(
		new Document("_id", 7839) 
		.append("name", "KING")
		.append("prenoms", Arrays.asList("Leroi", "Mokondji"))
		.append("adresse", new Document(
			"numero", 6)
			.append("rue", "Traverse en Escalier")
            .append("ville", "Valbonne")
            .append("codePostal", "06560")
            .append("Pays", "France"))
		.append("job", "Pr�sident") 
		.append("mgr", null) 
		.append("hiredate", "17-11-1981") 
		.append("sal", 5000)
		.append("comm", null)
		.append("deptno", 10),
		
		
		new Document(
		"_id", 7369) 
		.append("name", "SMITH")
		.append("prenoms", Arrays.asList("Will", "Williamson"))
		.append("adresse", new Document(
			"numero", 11)
			.append("rue", "Du capitol")
            .append("ville", "Toulouse")
            .append("codePostal", "31000")
            .append("Pays", "France"))
		.append("job", "Clerk") 
		.append("mgr", 7902) 
		.append("hiredate", "17-12-1980") 
		.append("sal", 800)
		.append("comm", null)
		.append("deptno", 20),
		
		new Document(
		"_id", 7499) 
		.append("name", "ALLEN")
		.append("prenoms", Arrays.asList("Isaora", "Maria"))
		.append("adresse", new Document(
			"numero", 11)
			.append("rue", "Des Mimosas")
            .append("ville", "Toulon")
            .append("codePostal", "83000")
            .append("Pays", "France"))
		.append("job", "Salesman") 
		.append("mgr", 7698) 
		.append("hiredate", "17-02-1981") 
		.append("sal", 1600)
		.append("comm", 300)
		.append("deptno", 30),


		new Document(
		"_id", 7521) 
		.append("name", "WARD")
		.append("prenoms", Arrays.asList("Julian", "Emery", "Andy"))
		.append("adresse", new Document(
			"numero", 11)
			.append("rue", "Place Garibaldi")
            .append("ville", "Nice")
            .append("codePostal", "06000")
            .append("Pays", "France"))
		.append("job", "Salesman") 
		.append("mgr", 7698) 
		.append("hiredate", "22-FEB-1981") 
		.append("sal", 1250)
		.append("comm", 500)
		.append("deptno", 30),


		new Document(
		"_id", 7566) 
		.append("name", "JONES")
		.append("prenoms", Arrays.asList("Julian"))
		.append("adresse", new Document(
			"numero", 11)
			.append("rue", "Pertinax")
            .append("ville", "Nice")
            .append("codePostal", "08000")
            .append("Pays", "France"))
		.append("job", "Manager") 
		.append("mgr", 7839) 
		.append("hiredate", "1-04-1981") 
		.append("sal", 2975)
		.append("comm", null)
		.append("deptno", 20),

		
		new Document(
		"_id", 7654) 
		.append("name", "MARTIN")
		.append("prenoms", Arrays.asList("Le pecheur"))
		.append("adresse", new Document(
			"numero", 10)
			.append("rue", "Canebiere")
            .append("ville", "Marseille")
            .append("codePostal", "13000")
            .append("Pays", "France"))
		.append("job", "Salesman") 
		.append("mgr", 7698) 
		.append("hiredate", "28-11-1981") 
		.append("sal", 1250)
		.append("comm", 1400)
		.append("deptno", 30),


		new Document(
		"_id", 7698) 
		.append("name", "BLAKE")
		.append("prenoms", Arrays.asList("Lenoir"))
		.append("adresse", new Document(
			"numero", 11)
			.append("rue", "Rond point des Africains")
            .append("ville", "Cogolin")
            .append("codePostal", "83310")
            .append("Pays", "France"))
		.append("job", "Manager") 
		.append("mgr", 7839) 
		.append("hiredate", "1-05-1981") 
		.append("sal", 2850)
		.append("comm", null)
		.append("deptno", 30),


		new Document(
		"_id", 7782) 
		.append("name", "CLARK")
		.append("prenoms", Arrays.asList("Jonson"))
		.append("adresse", new Document(
			"numero", 27)
			.append("rue", "Des Miracles")
            .append("ville", "Toulouse")
            .append("codePostal", "31000")
            .append("Pays", "France"))
		.append("job", "Manager") 
		.append("mgr", 7839) 
		.append("hiredate", "9-06-1981") 
		.append("sal", 2450)
		.append("comm", null)
		.append("deptno", 10),


		new Document(
		"_id", 7788) 
		.append("name", "SCOTT")
		.append("prenoms", Arrays.asList("Tiger"))
		.append("adresse", new Document(
			"numero", 75)
			.append("rue", "Jean Jaures")
            .append("ville", "Nice")
            .append("codePostal", "06000")
            .append("Pays", "France"))
		.append("job", "Analyst") 
		.append("mgr", 7566) 
		.append("hiredate", "09-12-1982") 
		.append("sal", 3000)
		.append("comm", null)
		.append("deptno", 20),

		
		new Document(
		"_id", 7844) 
		.append("name", "TURNER")
		.append("prenoms", Arrays.asList("Allan", "Johnson"))
		.append("adresse", new Document(
			"numero", 11)
			.append("rue", "d'Italie")
            .append("ville", "Nice")
            .append("codePostal", "06000")
            .append("Pays", "France"))
		.append("job", "Salesman") 
		.append("mgr", 7698) 
		.append("hiredate", "8-09-1981") 
		.append("sal", 1500)
		.append("comm", 0)
		.append("deptno", 30),

		
		new Document(
		"_id", 7876) 
		.append("name", "ADAMS")
		.append("prenoms", Arrays.asList("Le premier"))
		.append("adresse", new Document(
			"numero", 378)
			.append("rue", "Rue du Paradis")
            .append("ville", "Marseille")
            .append("codePostal", "13000")
            .append("Pays", "France"))
		.append("job", "Clerk") 
		.append("mgr", 7788) 
		.append("hiredate", "12-01-1983") 
		.append("sal", 1100)
		.append("comm", null)
		.append("deptno", 20),
		
		new Document(
		"_id", 7900) 
		.append("name", "JAMES")
		.append("prenoms", Arrays.asList("Bond"))
		.append("adresse", new BasicDBObject(
			"numero", 12)
			.append("rue", "Rue d'Angleterre")
            .append("ville", "Nice")
            .append("codePostal", "06000")
            .append("Pays", "France"))
		.append("job", "Clerk") 
		.append("mgr", 7698) 
		.append("hiredate", "9-12-1981") 
		.append("sal", 950)
		.append("comm", null)
		.append("deptno", 30),
		
		new Document(
		"_id", 7902) 
		.append("name", "FORD")
		.append("prenoms", Arrays.asList("James"))
		.append("adresse", new BasicDBObject(
			"numero", 11)
			.append("rue", "Des voitures")
            .append("ville", "Toulouse")
            .append("codePostal", "31000")
            .append("Pays", "France"))
		.append("job", "Analyst") 
		.append("mgr", 7566) 
		.append("hiredate", "3-12-1981") 
		.append("sal", 3000)
		.append("comm", null)
		.append("deptno", 20),

		new Document(
		"_id", 7934) 
		.append("name", "MILLER")
		.append("prenoms", Arrays.asList("Rosa", "Alison"))
		.append("adresse", new BasicDBObject(
			"numero", 11)
			.append("rue", "Place des �toiles")
            .append("ville", "Biarritz")
            .append("codePostal", "64200")
            .append("Pays", "France"))
		.append("job", "Clerk") 
		.append("mgr", 7782) 
		.append("hiredate", "23-01-1982") 
		.append("sal", 1300)
		.append("comm", null)
		.append("deptno", 10)
		
		);  
		this.insertManyEmployes(this.EmployeCollectionName, employes);
   }

   /**
	FE8 : Cette fonction permet de rechercher un employ� dans une collection
	connaissant son id.
	Travail � faire : compl�ter cette m�thode

   */

   public void getEmployeById(String nomCollection, Integer empId){

	   Document result = this.database.getCollection(nomCollection).find(eq("_id",empId)).first();
	   if(result != null){
		   System.out.println(result.toJson());
	   } else {
		   System.out.println("Pas d'employé avec cet ID");
	   }
   } 
   
   
    /**
	FE9 :Cette fonction permet de rechercher des employ�s dans une collection.
	Le parametre whereQuery : permet de passer des conditions de rechercher
	Le parametre projectionFields : permet d'indiquer les champs a afficher
	Le parametre sortFields : permet d'indiquer les champs de tri.
	
	Travail � faire : compl�ter cette m�thode

   */

   public void getEmployes(String nomCollection, 
   	Document whereQuery, 
	Document projectionFields,
	Document sortFields){
	   MongoCursor <Document> results = this.database.getCollection(nomCollection)
			   .find(whereQuery).projection(projectionFields).sort(sortFields).iterator();
	   if(results.hasNext()){
		   try {
			   while(results.hasNext()) {
				   System.out.println(results.next().toJson());
			   }
		   } finally {
			   results.close();
		   }
	   } else {
		   System.out.println("Aucune document trouvé");
	   }

   } 

    /**
	FE10 :Cette fonction permet de modifier des employ�s dans une collection.
	Le parametre whereQuery : permet de passer des conditions de recherche
	Le parametre updateExpressions : permet d'indiquer les champs a modifier
	Le parametre UpdateOptions : permet d'indiquer les options de mise a jour :
		.upSert : insere si le document n'existe pas
	
	Travail � faire : compl�ter cette m�thode

   */

   public void updateEmployes(String nomCollection, 
   	Document whereQuery, 
	Document updateExpressions, UpdateOptions updateOptions){
	   try {
		   UpdateResult result = this.database.getCollection(nomCollection)
				   .updateMany(whereQuery, updateExpressions, updateOptions);
		   System.out.println("Nombre de documents mis à jour: "+result.getModifiedCount());
	   } catch (MongoException ex) {
		   System.err.println(ex);
	   }
   }


    /**
	FE11 :Cette fonction permet de supprimer des employ�s dans une collection.
	Le parametre filters : permet de passer des conditions de recherche des employ�s a supprimer
	
	Travail � faire : compl�ter cette m�thode

 */
   public void deleteEmployes(String nomCollection, Document filters){
		try {
			DeleteResult result = this.database.getCollection(nomCollection).deleteMany(filters);
			System.out.println("Nombre de documents supprimes: "+result.getDeletedCount());
		} catch (MongoException ex) {
			System.err.println(ex);
		}
   } 	
   
   
   /**
	FE12 :Parcours un it�rateur et affiche les documents qui s'y trouvent
   */
   public void displayIterator(Iterator it, String message){
	System.out.println(" \n #### "+ message + " ################################");
	while(it.hasNext()) {
		System.out.println(it.next());
		
	}		
	
	
   }

    /**
	FE13 : Cette fonction permet d'effectuer une jointure entre les collections dept et employe
	Elle permet d'afficher les employ�s par d�partement.
	Le parametre localCollectionName : nom de la 1ere collection a joindre
	Le parametre ForeignCollectionName : nom de la 2eme collection a joindre
	Le parametre localColJoinFieldName : champ de jointure de la 1 ere collection
	Le parametre foreigColJoinFieldName :  champ de jointure de la 2 eme collection
	Le parametre filterFieldName : Champ de restriction
	Le parametre filterFieldType :  type du champ. Entier pour l'instant
	Le parametre filterFieldValue : Valeur du champ de restriction
	
	Travail � faire : compl�ter cette m�thode

	
   */

	   
   public void joinLocalAndforeignCollections(
	String localCollectionName, 
	String foreignCollectionName, 
	String localColJoinFieldName,
	String foreigColJoinFieldName,
	String filterFieldName,
	int filterFieldValue){
	   Bson pipeline = lookup(foreignCollectionName, localColJoinFieldName, foreigColJoinFieldName, this.EmployeCollectionName);
	   List<Document> employes = this.database.getCollection(localCollectionName)
			   .aggregate(Arrays.asList(pipeline,match(eq(filterFieldName,filterFieldValue))))
			   .into(new ArrayList<>());
	   employes.forEach(employe -> System.out.println(employe.toJson()));
	}

   /**
	FE14 : Cette fonction permet d'effectuer des op�rations de groupes
	dans la collection Employe sur le champ job.
	Elle renvoie :
		- La masse salariale par job
		- La moyenne des salaires par job
		- Le nombre de salaire par job.
	
	
	Travail � faire : compl�ter cette m�thode

   */

   public void groupByOnOneCollectionByJob(
	String localCollectionName
	){
	   AggregateIterable results = this.database.getCollection(localCollectionName)
			   .aggregate(Arrays.asList(group("$job",
							   Accumulators.sum("totalSalaire", "$sal"),
							   Accumulators.sum("totalJob",1),
							   Accumulators.avg("moyenne","$sal"))
					   ));
	   Iterator iterator = results.iterator();
	   while(iterator.hasNext()) {
		   Document next = (Document) iterator.next();
		   System.out.println(next.get("_id"));
		   System.out.println("Masse salariale: "+next.get("totalSalaire"));
		   System.out.println("Moyenne salaires: "+next.get("moyenne"));
		   System.out.println("Nombre de salaires: "+next.get("totalJob")+"\n");
	   }
	}
	

    /**
	FE15 : Cette fonction permet de cr�er un index dans la collection.
		- Le parametre localCollectionName : Nom de la collection
		- Le parametre indexName : Nom de l'indexe
		- Le parametre indexFieldName1 : Nom du 1er champ d'index
		- Le parametre indexFieldName2 : Nom du 2eme champ d'index s'il y a
		- Le parametre indexFieldName3 : Nom du 3eme champ d'index s'il y a
		- Le parametre indexType : Type d'index
		- Le parametre isAscendingIndex : true si ascendant, false sinon
		- Le parametre indexUnique : true si index unique false sinon
		
		Travail � faire : compl�ter cette m�thode

	
   */
	public void createEmployeIndexes(
		String localCollectionName,
		String indexName,
		String indexFieldName1,
		String indexFieldName2,
		String indexFieldName3,
		boolean isAscendingIndex,
		boolean indexUnique
	){
		ArrayList<String> indexes = new ArrayList<>();
		indexes.add(indexFieldName1);
		if(indexFieldName2 != null) {
			indexes.add(indexFieldName2);
		} else if(indexFieldName3 != null) {
			indexes.add(indexFieldName3);
		}
		IndexOptions indexOptions = new IndexOptions().unique(indexUnique).name(indexName);
		System.out.println("\n*********** dans createEmployeIndexes *****************");
		System.out.println("On ajoute un index avec le nom "+indexName);
		if(isAscendingIndex) {
			this.database.getCollection(localCollectionName).createIndex(Indexes.ascending(
					indexes
			),indexOptions);
		} else {
			this.database.getCollection(localCollectionName).createIndex(Indexes.descending(
					indexFieldName1, indexFieldName2, indexFieldName3
			),indexOptions);
		}
	}

    /**
	FE16 : Cette fonction affiche les informations sur tous les indexes d'une collection.
	
	Travail � faire : compl�ter cette m�thode

   */
	public void getAllIndexesOfACollection(
		String localCollectionName
	){
		System.out.println("\n*********** dans getAllIndexesOfACollection *****************");
		for (Document index : this.database.getCollection(localCollectionName).listIndexes()) {
			System.out.println(index.toJson());
		}
	}


   /**
	FE17 :Cette fonction supprime un index dans une collection connaissant son nom.
	
	Travail � faire : compl�ter cette m�thode

   */
	public void dropAIndexOfACollection(
		String localCollectionName,
		String indexName
	){
		System.out.println("\n*********** dans dropAIndexOfACollection *****************");
		System.out.println("On supprime l'index "+indexName);
		this.database.getCollection(localCollectionName).dropIndex(indexName);
		for (Document index : this.database.getCollection(localCollectionName).listIndexes()) {
			System.out.println(index.toJson());
		}
	}

}
