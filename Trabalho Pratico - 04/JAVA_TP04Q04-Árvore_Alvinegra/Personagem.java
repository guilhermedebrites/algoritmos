import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class Personagem {

    private UUID id;
    private String name;
    private ArrayList<String> alternate_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    private LocalDate dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;
    private String birthDateString;

    public Personagem(UUID id, String name, ArrayList<String> alternate_names, String house, String ancestry,
                      String species, String patronus, boolean hogwartsStaff, boolean hogwartsStudent, String actorName,
                      boolean alive, LocalDate dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour,
                      boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternate_names = alternate_names;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
        this.birthDateString = dateOfBirth != null ? dateOfBirth.toString() : "";
    }

    public Personagem() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAlternate_names() {
        return alternate_names;
    }

    public void setAlternate_names(ArrayList<String> alternate_names) {
        this.alternate_names = alternate_names;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public boolean getHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public boolean isWizard() {
        return wizard;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }

    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }

    public void imprimir(int x) {
        String alternateNamesStr = "{";
        if (alternate_names != null && !alternate_names.isEmpty()) {
            for (int i = 0; i < alternate_names.size(); i++) {
                alternateNamesStr += alternate_names.get(i) + ",";
            }
            alternateNamesStr = alternateNamesStr.replace("],", "}");
            alternateNamesStr = alternateNamesStr.replace("[", "");
        } else {
            alternateNamesStr = "{}";
        }

        DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dateOfBirth != null ? dateOfBirth.format(desiredFormatter) : "N/A";

        System.out.println("[" + x + " ## " + id + " ## " + name + " ## " + alternateNamesStr + " ## " + house
                + " ## " + ancestry + " ## " + species + " ## " + patronus
                + " ## " + hogwartsStaff + " ## " + hogwartsStudent + " ## "
                + actorName + " ## " + alive + " ## " + formattedDate + " ## "
                + yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## "
                + hairColour + " ## " + wizard + "]");
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int numComparacoes = 0;

        String nomeDoArquivo = "/tmp/characters.csv";

        Map<UUID, Personagem> personagens = new HashMap<>();
        ArvoreAlvinegra arvore = new ArvoreAlvinegra();

        Personagem personagem = new Personagem();
        personagem.ler(nomeDoArquivo, personagens);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String entrada = "";
        try {
            entrada = br.readLine();
            while (!entrada.equals("FIM")) {
                Personagem perso = personagens.get(UUID.fromString(entrada));
                arvore.inserir(perso);
                entrada = br.readLine();
            }

            entrada = br.readLine();
            while (!entrada.equals("FIM")) {
                System.out.print(entrada + " => ");
                String caminho = arvore.pesquisarCaminho(entrada);
                numComparacoes += arvore.getNumComparacoes();
                System.out.println(caminho);
                entrada = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); 
        long executionTime = endTime - startTime; 

        String logFilename = "808721_alvinegra.txt";
        try (FileWriter logFile = new FileWriter(logFilename)) {
            logFile.write("808721\t" + executionTime + " ms\t" + numComparacoes + " comparações\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ler(String nomeDoArquivo, Map<UUID, Personagem> mapper) {
        try {
            FileReader file = new FileReader(nomeDoArquivo);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();
            while ((line = buffer.readLine()) != null) {
                String[] atributos = line.split(Pattern.quote(";"));
                Personagem newPersonagem = new Personagem();

                newPersonagem.setId(UUID.fromString(atributos[0]));
                newPersonagem.setName(atributos[1]);

                ArrayList<String> alternates_names = new ArrayList<>();
                String[] namesAlternativos = atributos[2].split(Pattern.quote(","));

                for (String namesAlternativo : namesAlternativos) {
                    alternates_names.add(namesAlternativo.replace("'", ""));
                }

                newPersonagem.setAlternate_names(alternates_names);
                newPersonagem.setHouse(atributos[3]);
                newPersonagem.setAncestry(atributos[4]);
                newPersonagem.setSpecies(atributos[5]);
                newPersonagem.setPatronus(atributos[6]);
                newPersonagem.setHogwartsStaff(atributos[7].equalsIgnoreCase("VERDADEIRO"));
                newPersonagem.setHogwartsStudent(atributos[8].equalsIgnoreCase("VERDADEIRO"));
                newPersonagem.setActorName(atributos[9]);
                newPersonagem.setAlive(atributos[10].equalsIgnoreCase("VERDADEIRO"));

                LocalDate date = tentaPatternsDiferentes(atributos[12]);
                newPersonagem.setDateOfBirth(date);
                newPersonagem.setBirthDateString(atributos[12]);

                newPersonagem.setYearOfBirth(Integer.parseInt(atributos[13]));
                newPersonagem.setEyeColour(atributos[14]);
                newPersonagem.setGender(atributos[15]);
                newPersonagem.setHairColour(atributos[16]);
                newPersonagem.setWizard(atributos[17].equalsIgnoreCase("VERDADEIRO"));

                mapper.put(newPersonagem.getId(), newPersonagem);
            }
            buffer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LocalDate tentaPatternsDiferentes(String dateString) {
        String[] patterns = {"dd-MM-yyyy", "dd-M-yyyy"};

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
            }
        }

        throw new DateTimeParseException("Não foi possível converter a string em uma data " + dateString, dateString, 0);
    }
}

class No {
    public Personagem elemento;
    public No esq, dir, pai;
    public boolean cor; 

    public No(Personagem elemento) {
        this(elemento, null, null, null, true);
    }

    public No(Personagem elemento, No esq, No dir, No pai, boolean cor) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.pai = pai;
        this.cor = cor;
    }
}

class ArvoreAlvinegra {
    private No raiz;
    private int numComparacoes;

    public ArvoreAlvinegra() {
        raiz = null;
        numComparacoes = 0;
    }

    public int getNumComparacoes() {
        return numComparacoes;
    }

    public void inserir(Personagem elemento) {
        No novo = new No(elemento);
        if (raiz == null) {
            raiz = novo;
            raiz.cor = false; // Raiz é sempre preta
        } else {
            inserir(raiz, novo);
            balancearInsercao(novo);
        }
    }

    private void inserir(No atual, No novo) {
        if (novo.elemento.getName().compareTo(atual.elemento.getName()) < 0) {
            if (atual.esq == null) {
                atual.esq = novo;
                novo.pai = atual;
            } else {
                inserir(atual.esq, novo);
            }
        } else {
            if (atual.dir == null) {
                atual.dir = novo;
                novo.pai = atual;
            } else {
                inserir(atual.dir, novo);
            }
        }
    }

    private void balancearInsercao(No no) {
        while (no != raiz && no.pai.cor == true) {
            if (no.pai == no.pai.pai.esq) {
                No tio = no.pai.pai.dir;
                if (tio != null && tio.cor == true) {
                    no.pai.cor = false;
                    tio.cor = false;
                    no.pai.pai.cor = true;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.dir) {
                        no = no.pai;
                        rotacaoEsquerda(no);
                    }
                    no.pai.cor = false;
                    no.pai.pai.cor = true;
                    rotacaoDireita(no.pai.pai);
                }
            } else {
                No tio = no.pai.pai.esq;
                if (tio != null && tio.cor == true) {
                    no.pai.cor = false;
                    tio.cor = false;
                    no.pai.pai.cor = true;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.esq) {
                        no = no.pai;
                        rotacaoDireita(no);
                    }
                    no.pai.cor = false;
                    no.pai.pai.cor = true;
                    rotacaoEsquerda(no.pai.pai);
                }
            }
        }
        raiz.cor = false;
    }

    private void rotacaoEsquerda(No no) {
        No direito = no.dir;
        no.dir = direito.esq;
        if (direito.esq != null) {
            direito.esq.pai = no;
        }
        direito.pai = no.pai;
        if (no.pai == null) {
            raiz = direito;
        } else if (no == no.pai.esq) {
            no.pai.esq = direito;
        } else {
            no.pai.dir = direito;
        }
        direito.esq = no;
        no.pai = direito;
    }

    private void rotacaoDireita(No no) {
        No esquerdo = no.esq;
        no.esq = esquerdo.dir;
        if (esquerdo.dir != null) {
            esquerdo.dir.pai = no;
        }
        esquerdo.pai = no.pai;
        if (no.pai == null) {
            raiz = esquerdo;
        } else if (no == no.pai.dir) {
            no.pai.dir = esquerdo;
        } else {
            no.pai.esq = esquerdo;
        }
        esquerdo.dir = no;
        no.pai = esquerdo;
    }

    public String pesquisarCaminho(String nome) {
        numComparacoes = 0;
        return pesquisarCaminho(nome, raiz, "raiz");
    }

    private String pesquisarCaminho(String nome, No no, String caminho) {
        if (no == null) {
            return caminho + " NAO";
        }

        numComparacoes++;
        if (nome.compareTo(no.elemento.getName()) < 0) {
            return pesquisarCaminho(nome, no.esq, caminho + " esq");
        } else if (nome.compareTo(no.elemento.getName()) > 0) {
            return pesquisarCaminho(nome, no.dir, caminho + " dir");
        } else {
            return caminho + " SIM";
        }
    }
}
