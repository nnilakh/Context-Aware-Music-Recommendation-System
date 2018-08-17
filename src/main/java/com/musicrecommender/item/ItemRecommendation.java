package com.musicrecommender.item;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import com.csvreader.CsvWriter;
import com.mkyong.rest.DbConnection;

public class ItemRecommendation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			DataModel model = new FileDataModel(new File("data/user_song.csv"));
			
//			JDBCDataModel dataModel = new JDBCDataModel(dataSource, "my_prefs_table", "my_user_column",
//				    "my_item_column", "my_pref_value_column", "my_timestamp_column");
			
			ItemSimilarity item = new LogLikelihoodSimilarity(model);
			
			DbConnection con = new DbConnection();
			
			GenericItemBasedRecommender gm = new GenericItemBasedRecommender(model, item);
			int x=1;
			for(LongPrimitiveIterator prim = model.getItemIDs(); prim.hasNext();){
				long itemid = prim.nextLong();
				List<RecommendedItem> listrecomm = gm.mostSimilarItems(itemid, 10);
				
				String outputFile="data/recommendItem.csv";
				boolean alreadyExists = new File(outputFile).exists();
				try {
					// use FileWriter constructor that specifies open for appending
					CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
					
					// if the file didn't already exist then we need to write out the header line
					if (!alreadyExists)
					{
						csvOutput.write("itemid");
						csvOutput.write("recoitemid");
						csvOutput.write("value");
						csvOutput.endRecord();
					}
				
				
				
				for(RecommendedItem recommendation : listrecomm){
					System.out.println(itemid+","+recommendation.getItemID()+","+recommendation.getValue());
					
					//Database call
					csvOutput.write(Long.toString(itemid));
					csvOutput.write(Long.toString(recommendation.getItemID()));
					csvOutput.write(Float.toString(recommendation.getValue()));
					csvOutput.endRecord();
					
					con.setUserRecommendation(itemid, recommendation.getItemID(), recommendation.getValue());
				}
				x++;
				
				//if(x>10) System.exit(1);
			}
			catch(IOException e1){}
			}	
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TasteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

}
