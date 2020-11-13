/*
 Program author: Analia Trevino-Flitton
 */


/*
Read Genbank file, output the accession number, length of sequence, & description on 1st line
Then print the sequence on the next line
 */


import org.biojava.bio.BioException;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;
import org.biojavax.bio.seq.RichSequence.IOTools;

import java.io.*;
import javax.swing.JFileChooser;

public class GenBankParser {

    private static JFileChooser ourChooser = new JFileChooser(".");

    /**
     * Open a file through a FileChooser
     */
    public static BufferedReader openFile(){
        int retval = ourChooser.showOpenDialog(null);
        BufferedReader br = null;

        if (retval == JFileChooser.APPROVE_OPTION){
            File file = ourChooser.getSelectedFile();
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                System.out.println("trouble reading "+file.getName());
                e.printStackTrace();
            }
        }
        return br;
    }


    public static void main(String[] args) throws BioException{
        BufferedReader br = openFile();


        //Read Genbank file
        RichSequenceIterator it =  IOTools.readGenbankDNA(br, null);

        // Get values for each seq
        while (it.hasNext()){
            RichSequence s = it.nextRichSequence();

            //Output
            System.out.print("\nAccession: " + s.getAccession() + " | Length: " + s.seqString().length() + " | "+ s.getDescription());
            System.out.println("\n" + s.seqString());
        }
        System.out.println("\nBy: Analia Treviño-Flitton");

    }
}

