const questions = [
    {
        question: "1. Quel est l’objectif principal d’une attaque par hameçonnage (phishing) ?",
        options: [
            "a) Voler des informations personnelles",
            "b) Améliorer la vitesse d’Internet",
            "c) Optimiser le fonctionnement des antivirus",
            "d) Tester la connexion d’un utilisateur"
        ],
        answer: "a"
    },
    {
        question: "2. Quel fichier est le plus susceptible de contenir un malware ?",
        options: [
            "a) PDF d’un site officiel",
            "b) Image JPEG envoyée par un ami",
            "c) Exécutable (.exe) depuis une source inconnue",
            "d) Fichier texte (.txt) avec uniquement du texte"
        ],
        answer: "c"
    },
    {
        question: "3. Quelle est la principale méthode utilisée dans une attaque par force brute ?",
        options: [
            "a) Exploiter une faille logicielle",
            "b) Tester toutes les combinaisons possibles d’un mot de passe",
            "c) Envoyer un virus caché",
            "d) Créer une fausse page de connexion"
        ],
        answer: "b"
    },
    {
        question: "4. Que signifie le terme 'ransomware' ?",
        options: [
            "a) Un logiciel espion",
            "b) Programme qui crypte les fichiers et exige une rançon",
            "c) Virus via clé USB uniquement",
            "d) Attaque visant uniquement les sites commerciaux"
        ],
        answer: "b"
    },
    {
        question: "5. Quel comportement réduit le risque d’une attaque par ingénierie sociale ?",
        options: [
            "a) Partager ses mots de passe",
            "b) Cliquer sans vérifier",
            "c) Ne jamais divulguer d’infos sensibles",
            "d) Désactiver l’antivirus"
        ],
        answer: "c"
    },
    {
        question: "6. Quel est le rôle d’un pare-feu (firewall) ?",
        options: [
            "a) Bloquer les spams",
            "b) Protéger un réseau contre les accès non autorisés",
            "c) Augmenter la vitesse de connexion",
            "d) Remplacer un antivirus"
        ],
        answer: "b"
    },
    {
        question: "7. Quelle technique est souvent utilisée via une clé USB ?",
        options: [
            "a) Injection SQL",
            "b) MITM",
            "c) Cheval de Troie (Trojan)",
            "d) DDoS"
        ],
        answer: "c"
    },
    {
        question: "8. Quel est l’objectif d’une attaque par déni de service (DDoS) ?",
        options: [
            "a) Voler des mots de passe",
            "b) Rendre un service indisponible",
            "c) Espionner les communications",
            "d) Désactiver les antivirus"
        ],
        answer: "b"
    },
    {
        question: "9. Quelle mesure de sécurité est la plus efficace ?",
        options: [
            "a) Mot de passe simple",
            "b) Activer l’authentification à deux facteurs (2FA)",
            "c) Même mot de passe partout",
            "d) Post-it sur l’écran"
        ],
        answer: "b"
    },
    {
        question: "10. Quel type d’attaque repose sur l’injection de code dans une BDD ?",
        options: [
            "a) Force brute",
            "b) Ingénierie sociale",
            "c) Injection SQL",
            "d) Phishing"
        ],
        answer: "c"
    }
];

// Ajouter les questions dynamiquement
const container = document.getElementById("quiz-form");
const questionContainer = document.getElementById("questions-container");

questions.forEach((q, index) => {
    const block = document.createElement("div");
    block.className = "question-block";

    const qTitle = document.createElement("p");
    qTitle.innerHTML = `<strong>${q.question}</strong>`;
    block.appendChild(qTitle);

    q.options.forEach((option, i) => {
        const label = document.createElement("label");
        label.innerHTML = `<input type="radio" name="q${index + 1}" value="${option.charAt(0)}"> ${option}`;
        block.appendChild(label);
        block.appendChild(document.createElement("br"));
    });

    questionContainer.appendChild(block);
});

// Générer le bouton de soumission
const submitBtn = document.createElement("button");
submitBtn.type = "submit";
submitBtn.id = "submit-btn";
submitBtn.textContent = "Valider mes réponses";
submitBtn.style.marginTop = "20px";
container.appendChild(submitBtn); // On ajoute le bouton ici, après les questions

const timerElement = document.getElementById("time");
let time = 120; // Temps en secondes (2 minutes)

// Démarrer le compte à rebours
let interval = setInterval(() => {
    time--; // Décrémenter le temps
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    timerElement.textContent = `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;

    // Si le temps est écoulé
    if (time <= 0) {
        clearInterval(interval);
        alert("Temps écoulé ! Le formulaire sera soumis.");
        document.getElementById("quiz-form").submit(); // Soumettre le quiz automatiquement
    }
}, 1000);


// Gestion de la soumission du formulaire
document.getElementById("quiz-form").addEventListener("submit", function (e) {
    e.preventDefault(); // Empêcher la soumission classique

    let score = 0;

    questions.forEach((q, index) => {
        const selected = document.querySelector(`input[name="q${index + 1}"]:checked`);
        if (selected && selected.value === q.answer) {
            score++;
        }
    });

    let resultMessage = "";
    if (score >= 8) {
        resultMessage = "🏆 Expert en cybersécurité !";
    } else if (score >= 5) {
        resultMessage = "⚠ Bon niveau, mais encore des points à améliorer.";
    } else {
        resultMessage = "🚨 Attention ! Il est temps de renforcer vos connaissances.";
    }

    // Afficher le score final
    const scoreDiv = document.getElementById("score");
    scoreDiv.innerHTML = `<h2>Score final : ${score}/10</h2><p>${resultMessage}</p>`;
    scoreDiv.style.display = "block";

    // Désactiver les radios après la soumission
    document.querySelectorAll("input[type=radio]").forEach(input => input.disabled = true);

    // Masquer le bouton "Valider"
    submitBtn.style.display = "none"; // On cache le bouton après soumission

    // Arrêter le timer quand le formulaire est soumis
    clearInterval(interval);
});
