import java.util.*;
import java.io.*;
public class ColonyExplorerRunner {

	public static void main(String[] args) throws IOException {

		List<Character> cells= new ArrayList<Character>();
		//ExploreAndLabelColony(y,0,label,cells);
		char[][] finalCell=ColonyExplorerer( cells,'A');

		for(int i=65;i<90;i++)
		{if(Collections.frequency(ExploreAndLabelColony(finalCell,0,2,cells,(char)i),(char)i)>0)
			System.out.println("the size of the cell "+ (char)i +" is: "+Collections.frequency(cells,(char)i));
		}

		for(int i=0;i<finalCell.length;i++)
		{System.out.println("");
		for(int j=0;j<finalCell[i].length;j++)
			System.out.print(finalCell[i][j]);
		}
		
		try {
			String fileName = "recursive output.txt";
			Writer fileWriter=new FileWriter(fileName,true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("\n*******************************************");
			for(int i=65;i<90;i++)
				if(Collections.frequency(ExploreAndLabelColony(finalCell,0,2,cells,(char)i),(char)i)>0)
					bufferedWriter.write("\n the size of the cell "+ (char)i +" is: "+Collections.frequency(cells,(char)i));;
			bufferedWriter.write("\n*******************************************\n");
			bufferedWriter.write("\nThe Explored cell is: \n");
			for(int i=0;i<finalCell.length;i++)
			{bufferedWriter.write("\n");
			for(int j=0;j<finalCell[i].length;j++)
				bufferedWriter.write(finalCell[i][j]);
			}
			bufferedWriter.write("\n*******************************************\n\n\n");
			bufferedWriter.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found, sorry");
		}
	}


	public static List ExploreAndLabelColony(char Colonies[][],int x,int y,List<Character> cells, char label)  {


		if(x>=0 && x<Colonies.length && y<Colonies[x].length) 
		{if(Colonies[x][y]=='1')
		{cells.add(label);
		Colonies[x][y]=label;
		ExploreAndLabelColony(Colonies,x,y,cells,label);
		}
		}

		if(x+1<Colonies.length) {
			if(Colonies[x+1][y]=='1')
			{
				Colonies[x+1][y]=label;
				cells.add(label);
				ExploreAndLabelColony(Colonies,x+1,y,cells, label);
			}
			if(y-1>=0 && Colonies[x+1][y-1]=='1' )
			{
				cells.add(label);
				Colonies[x+1][y-1]=label;
				ExploreAndLabelColony(Colonies,x+1,y-1,cells,label);
			}	

			if(y+1<Colonies[x+1].length && Colonies[x+1][y+1]=='1' )
			{cells.add(label);

			Colonies[x+1][y+1]=label;
			ExploreAndLabelColony(Colonies,x+1,y+1,cells,label);
			}
		}
		//-----------------------------


		if(x-1>=0 && x+1<Colonies.length ) {
			if(Colonies[x][y]=='1')
			{cells.add(label);
			Colonies[x][y]=label;
			ExploreAndLabelColony(Colonies,x,y,cells,label);

			}

			if(Colonies[x-1][y]=='1')
			{cells.add(label);

			Colonies[x-1][y]=label;
			ExploreAndLabelColony(Colonies,x-1,y,cells,label);
			}

			if(y-1>=0 && Colonies[x-1][y-1]=='1')
			{cells.add(label);

			Colonies[x-1][y-1]=label;
			ExploreAndLabelColony(Colonies,x-1,y-1,cells,label);
			}

			if(y+1<Colonies[x-1].length && Colonies[x-1][y+1]=='1')
			{cells.add(label);

			Colonies[x-1][y+1]=label;
			ExploreAndLabelColony(Colonies,x-1,y+1,cells,label);
			}
		}
		//------------------------

		if(y-1>=0 && Colonies[x][y-1]=='1')
		{cells.add(label);

		Colonies[x][y-1]=label;
		ExploreAndLabelColony(Colonies,x,y-1,cells,label);
		}


		if(y+1<Colonies[x].length && Colonies[x][y+1]=='1')
		{cells.add(label);

		Colonies[x][y+1]=label;
		ExploreAndLabelColony(Colonies,x,y+1,cells,label);
		}
		return cells;
	}


	public static char[][] ColonyExplorerer(List<Character> cells,char label) {

		int randomx=(int)(Math.random()*(20-5+1)+5);
		int randomy=(int)(Math.random()*(20-5+1)+5);
		char Colonies[][] = new char[randomx][randomy];

		for(int i=0;i<Colonies.length;i++)
			for(int j=0;j<Colonies[i].length;j++)
			{
				Random rn= new Random();
				int a=(rn.nextInt(49-48+1)+48);
				Colonies[i][j]=(char)(a);
			}


		for(int i=0;i<Colonies.length;i++)
			for(int j=0;j<Colonies[i].length;j++)
			{
				if (Colonies[i][j]=='1')
				{ExploreAndLabelColony(Colonies,i,j,cells,label++);
				}
			}

		for(int i=0;i<Colonies.length;i++)
			for(int j=0;j<Colonies[i].length;j++)
			{
				if (Colonies[i][j]=='0')
				{Colonies[i][j]='-';
				}
			}
		return Colonies;
	}


}
