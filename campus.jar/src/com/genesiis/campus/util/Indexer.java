package com.genesiis.campus.util;

//20161027 PN c11-criteria-based-filter-search copied classes from Internet to test Lucene.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.FSDirectory;

import com.genesiis.campus.util.ConnectionManager;

/**
 * This class reads the input files from the data directory, creates indexes
 * and writes them in the index directory
 *
 *
 */
public class Indexer {
	private IndexWriter indexWriter;
	
	/*Location of directory where index files are stored */
	private String INDEX_DIRECTORY ;
	public static final Path indexDirectory= Paths.get("./indexdir");
	/*Location of data directory */
	private String dataDirectory ;
	
	public Indexer(String indexDirectory, String dataDirectory){
		this.INDEX_DIRECTORY = indexDirectory ;
		this.dataDirectory = dataDirectory ;
	}
	
	/**
	 * This method creates an instance of IndexWriter which is used
	 * to add Documents and write indexes on the disc.
	 */
	void createIndexWriter(){
		if(indexWriter == null){
			try{
				//Create instance of Directory where index files will be stored
				//Path fsDirectory =  FSDirectory.getDirectory();
				FSDirectory fsDirectory = FSDirectory.open(indexDirectory);
				/* Create instance of analyzer, which will be used to tokenize
				the input data */
//				Analyzer standardAnalyzer = new StandardAnalyzer();
//				//Create a new index
//				boolean create = true;
//				//Create the instance of deletion policy
//				IndexDeletionPolicy deletionPolicy = 
//										new KeepOnlyLastCommitDeletionPolicy(); 
//				indexWriter =
//					 new IndexWriter(fsDirectory,standardAnalyzer,create,
//							 deletionPolicy,IndexWriter.MaxFieldLength.UNLIMITED);
				
				/** defining Analyzer */
				Analyzer standardAnalyzer = new StandardAnalyzer();

				/** preparing config for indexWriter */
				IndexWriterConfig writerConfig = new IndexWriterConfig(standardAnalyzer);
				/** Create a new index in the directory, removing any previously indexed documents */
				writerConfig.setOpenMode(OpenMode.CREATE);
				/**
				 * Optional: for better indexing performance, if you are indexing many documents,<BR>
				 * increase the RAM buffer. But if you do this, increase the max heap size to the JVM (eg add -Xmx512m or -Xmx1g):
				 */
				// writerConfig.setRAMBufferSizeMB(256.0);

				IndexWriter iWriter = new IndexWriter(fsDirectory, writerConfig);
				
				
			}catch(IOException ie){
				System.out.println("Error in creating IndexWriter");
				throw new RuntimeException(ie);
			}
		}
	}
	
	/**
	 * This method reads data directory and loads all properties files.
	 * It extracts  various fields and writes them to the index using IndexWriter.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws SQLException 
	 */
	void indexData() throws FileNotFoundException, IOException, SQLException{
		
		Connection conn = ConnectionManager.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT [CODE],[sender],[receiver],[date],[month],[subject],[message],[emaildoc] FROM [CAMPUS].[Messagesample]";
		ResultSet rs = stmt.executeQuery(sql);

		
		while (rs.next()) {
			String sender = rs.getString("sender");
			String receiver = rs.getString("receiver");
			String date = rs.getString("date");
			String month = rs.getString("month");
			String subject = rs.getString("subject");
			String message = rs.getString("message");
			String emaildoc = rs.getString("emaildoc");
			
			Field senderField =
					new Field("sender",sender,Field.Store.YES,Field.Index.NOT_ANALYZED);
				
				Field receiverfield = 
					new Field("receiver",receiver,Field.Store.NO,Field.Index.NOT_ANALYZED);
				
				Field subjectField = 
					new Field("subject",subject,Field.Store.YES,Field.Index.ANALYZED);
				
				if(subject.toLowerCase().indexOf("pune") != -1){
					// Display search results that contain pune in their subject first by setting boost factor
					subjectField.setBoost(2.2F);
				}
				
				Field emaildatefield = 
					new Field("date",date,Field.Store.NO,Field.Index.NOT_ANALYZED); 
				
				Field monthField = 
					new Field("month",month,Field.Store.NO,Field.Index.NOT_ANALYZED); 
				
				Field messagefield = 
					new Field("message",message,Field.Store.NO,Field.Index.ANALYZED);
				
				Field emailDocField =
					new Field("emailDoc",emaildoc,Field.Store.YES,
							Field.Index.NO);
				
				// Add these fields to a Lucene Document
				Document doc = new Document();
				doc.add(senderField);
				doc.add(receiverfield);
				doc.add(subjectField);
				doc.add(emaildatefield);
				doc.add(monthField);
				doc.add(messagefield);
				doc.add(emailDocField);
//				if(sender.toLowerCase().indexOf("job")!=-1){
//					//Display search results that contain 'job' in their sender email address
//					doc.setBoost(2.1F);
//				}
				
				//Step 3: Add this document to Lucene Index.
				indexWriter.addDocument(doc);
		}
		
//		File[] files = getFilesToBeIndxed();
//		for(File file:files){
//			Properties properties = new Properties();
//			properties.load(new FileInputStream(file));
			
			/*Step 1. Prepare the data for indexing. Extract the data. */
//			String sender = properties.getProperty("sender");
//			String receiver = properties.getProperty("receiver");
//			String date = properties.getProperty("date");
//			String month = properties.getProperty("month");
//			String subject = properties.getProperty("subject");
//			String message = properties.getProperty("message");
//			String emaildoc = file.getAbsolutePath();
			
			/*Step 2. Wrap the data in the Fields and add them to a Document */
			
			/* We plan to show the value of sender, subject and email document 
			   location along with the search results,for this we need to 
			   store their values in the index 		  	 
			 */
			
//			Field senderField =
//				new Field("sender",sender,Field.Store.YES,Field.Index.NOT_ANALYZED);
//			
//			Field receiverfield = 
//				new Field("receiver",receiver,Field.Store.NO,Field.Index.NOT_ANALYZED);
//			
//			Field subjectField = 
//				new Field("subject",subject,Field.Store.YES,Field.Index.ANALYZED);
//			
//			if(subject.toLowerCase().indexOf("pune") != -1){
//				// Display search results that contain pune in their subject first by setting boost factor
//				subjectField.setBoost(2.2F);
//			}
			
//			Field emaildatefield = 
//				new Field("date",date,Field.Store.NO,Field.Index.NOT_ANALYZED); 
//			
//			Field monthField = 
//				new Field("month",month,Field.Store.NO,Field.Index.NOT_ANALYZED); 
//			
//			Field messagefield = 
//				new Field("message",message,Field.Store.NO,Field.Index.ANALYZED);
//			
//			Field emailDocField =
//				new Field("emailDoc",emaildoc,Field.Store.YES,
//						Field.Index.NO);
			
//			// Add these fields to a Lucene Document
//			Document doc = new Document();
//			doc.add(senderField);
//			doc.add(receiverfield);
//			doc.add(subjectField);
//			doc.add(emaildatefield);
//			doc.add(monthField);
//			doc.add(messagefield);
//			doc.add(emailDocField);
//			if(sender.toLowerCase().indexOf("job")!=-1){
//				//Display search results that contain 'job' in their sender email address
//				doc.setBoost(2.1F);
//			}
			
//			//Step 3: Add this document to Lucene Index.
//			indexWriter.addDocument(doc);
//		}
		/* Requests an "optimize" operation on an index, priming the
		index for the fastest available search */
//		indexWriter.optimize();
		/*
		 * Commits all changes to the index and closes all associated files. 
		 */
		/** Closing iWriter */
		indexWriter.commit();
		indexWriter.close();
		
		/** Closing JDBC connection */
		rs.close();
		stmt.close();
		conn.close();
	}
	
	private File[] getFilesToBeIndxed(){
		File dataDir  = new File(dataDirectory);
		if(!dataDir.exists()){
			throw new RuntimeException(dataDirectory+" does not exist");
		}
		File[] files = dataDir.listFiles();
		return files;
	}
	
}
