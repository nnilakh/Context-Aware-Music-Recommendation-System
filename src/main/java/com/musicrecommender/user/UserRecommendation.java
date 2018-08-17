package com.musicrecommender.user;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.csvreader.CsvWriter;
import com.mkyong.rest.DbConnection;

public class UserRecommendation {

	/**
	 * @param args
	 */
	public  void getUserRecommendation() {
		// TODO Auto-generated method stub

		DataModel model;
		try {
			model = new FileDataModel(new File("data/user_song.csv"));
		
		UserSimilarity user = new LogLikelihoodSimilarity(model);
		
		UserNeighborhood neighbourhood= new NearestNUserNeighborhood(10, user, model);
		
		GenericUserBasedRecommender gm = new GenericUserBasedRecommender(model,neighbourhood, user);

		DbConnection con= new DbConnection();
		int x=1;
		for(LongPrimitiveIterator prim = model.getUserIDs(); prim.hasNext();){
			long itemid = prim.nextLong();
			List<RecommendedItem> listrecomm = gm.recommend(itemid, 7);
	
			String outputFile="data/recommend.csv";
			boolean alreadyExists = new File(outputFile).exists();
			try {
				// use FileWriter constructor that specifies open for appending
				CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
				
				// if the file didn't already exist then we need to write out the header line
				if (!alreadyExists)
				{
					csvOutput.write("userid");
					csvOutput.write("recoitemid");
					csvOutput.write("value");
					csvOutput.endRecord();
				}
			
			
			
			for(RecommendedItem recommendation : listrecomm){
				System.out.println(itemid+","+recommendation.getItemID()+","+recommendation.getValue());
			
				csvOutput.write(Long.toString(itemid));
				csvOutput.write(Long.toString(recommendation.getItemID()));
				csvOutput.write(Float.toString(recommendation.getValue()));
				csvOutput.endRecord();
				
				con.setUserRecommendation(itemid, recommendation.getItemID(), recommendation.getValue());
			}
			x++;
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

	public static void main(String args[]) {

		UserRecommendation u = new UserRecommendation();
		u.getUserRecommendation();
	}

}
