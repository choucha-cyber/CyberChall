const questionSecurite = [
    {
        questionS: "1. Quel est l’objectif principal d’une attaque par hameçonnage (phishing) ?",
        optionS: [
            "a) Voler des informations personnelles",
            "b) Améliorer la vitesse d’Internet",
            "c) Optimiser le fonctionnement des antivirus",
            "d) Tester la connexion d’un utilisateur"
        ],
        answerS: "a"
    },
    {
        questionS: "2. Quel fichier est le plus susceptible de contenir un malware ?",
        optionS: [
            "a) PDF d’un site officiel",
            "b) Image JPEG envoyée par un ami",
            "c) Exécutable (.exe) depuis une source inconnue",
            "d) Fichier texte (.txt) avec uniquement du texte"
        ],
        answerS: "c"
    },
    {
        questionS: "3. Quelle est la principale méthode utilisée dans une attaque par force brute ?",
        optionS: [
            "a) Exploiter une faille logicielle",
            "b) Tester toutes les combinaisons possibles d’un mot de passe",
            "c) Envoyer un virus caché",
            "d) Créer une fausse page de connexion"
        ],
        answerS: "b"
    },
    {
        questionS: "4. Que signifie le terme 'ransomware' ?",
        optionS: [
            "a) Un logiciel espion",
            "b) Programme qui crypte les fichiers et exige une rançon",
            "c) Virus via clé USB uniquement",
            "d) Attaque visant uniquement les sites commerciaux"
        ],
        answerS: "b"
    },
    {
        questionS: "5. Quel comportement réduit le risque d’une attaque par ingénierie sociale ?",
        optionS: [
            "a) Partager ses mots de passe",
            "b) Cliquer sans vérifier",
            "c) Ne jamais divulguer d’infos sensibles",
            "d) Désactiver l’antivirus"
        ],
        answerS: "c"
    },
    {
        questionS: "6. Quel est le rôle d’un pare-feu (firewall) ?",
        optionS: [
            "a) Bloquer les spams",
            "b) Protéger un réseau contre les accès non autorisés",
            "c) Augmenter la vitesse de connexion",
            "d) Remplacer un antivirus"
        ],
        answerS: "b"
    },
    {
        questionS: "7. Quelle technique est souvent utilisée via une clé USB ?",
        optionS: [
            "a) Injection SQL",
            "b) MITM",
            "c) Cheval de Troie (Trojan)",
            "d) DDoS"
        ],
        answerS: "c"
    },
    {
        questionS: "8. Quel est l’objectif d’une attaque par déni de service (DDoS) ?",
        optionS: [
            "a) Voler des mots de passe",
            "b) Rendre un service indisponible",
            "c) Espionner les communications",
            "d) Désactiver les antivirus"
        ],
        answerS: "b"
    },
    {
        questionS: "9. Quelle mesure de sécurité est la plus efficace ?",
        optionS: [
            "a) Mot de passe simple",
            "b) Activer l’authentification à deux facteurs (2FA)",
            "c) Même mot de passe partout",
            "d) Post-it sur l’écran"
        ],
        answerS: "b"
    },
    {
        questionS: "10. Quel type d’attaque repose sur l’injection de code dans une BDD ?",
        optionS: [
            "a) Force brute",
            "b) Ingénierie sociale",
            "c) Injection SQL",
            "d) Phishing"
        ],
        answerS: "c"
    }
];
// Ajouter les questions dynamiquement
const containerSecurite = document.getElementById("quiz-form-securite");
const questionContainerSecurite = document.getElementById("questions-container-securite");

questionSecurite.forEach((s, index) => {
    const blockS = document.createElement("div");
    blockS.className = "question-block-securite";

    const qTitleS = document.createElement("p");
    qTitleS.innerHTML = `<strong>${s.questionSecurite}</strong>`;
    blockS.appendChild(qTitleS);

    s.optionS.forEach((option, i) => {
        const labelS = document.createElement("label");
        labelS.innerHTML = `<input type="radio" name="q${index + 1}" value="${option.charAt(0)}"> ${option}`;
        blockS.appendChild(labelS);
        blockS.appendChild(document.createElement("br"));
    });

    questionContainerSecurite.appendChild(blockS);
});

// Générer le bouton de soumission
const submitBtnS = document.createElement("button");
submitBtnS.type = "submit";
submitBtnS.id = "submit-btn-securite";
submitBtnS.textContent = "Valider mes réponses";
submitBtnS.style.marginTop = "20px";
containerSecurite.appendChild(submitBtnS); // On ajoute le bouton ici, après les questions

const timerElementS = document.getElementById("quiz-time-securite");
let timeS = 120; // Temps en secondes (2 minutes)

// Démarrer le compte à rebours
let intervalS = setInterval(() => {
    timeS--; // Décrémenter le temps
    const minutesS = Math.floor(timeS / 60);
    const secondsS = timeS % 60;
    timerElementS.textContent = `${String(minutesS).padStart(2, '0')}:${String(secondsS).padStart(2, '0')}`;

    // Si le temps est écoulé
    if (timeS <= 0) {
        clearInterval(intervalS);
        alert("Temps écoulé ! Le formulaire sera soumis.");
        document.getElementById("quiz-form-securite").submit(); // Soumettre le quiz automatiquement
    }
}, 1000);


// Gestion de la soumission du formulaire
document.getElementById("quiz-form-securite").addEventListener("submit", function (e) {
    e.preventDefault(); // Empêcher la soumission classique

    let scoreS = 0;

    questionSecurite.forEach((s, index) => {
        const selectedS = document.querySelector(`input[name="q${index + 1}"]:checked`);
        if (selectedS && selectedS.value === s.answerS) {
            scoreS++;
        }
    });

    let resultMessageS = "";
    if (scoreS >= 8) {
        resultMessageS = "🏆 Expert en cybersécurité !";
    } else if (scoreS >= 5) {
        resultMessageS = "⚠ Bon niveau, mais encore des points à améliorer.";
    } else {
        resultMessageS = "🚨 Attention ! Il est temps de renforcer vos connaissances.";
    }

    // Afficher le score final
    const scoreDivS = document.getElementById("quiz-score-securite");
    scoreDivS.innerHTML = `<h2>Score final : ${scoreS}/10</h2><p>${resultMessageS}</p>`;
    scoreDivS.style.display = "block";

    // Désactiver les radios après la soumission
    document.querySelectorAll("input[type=radio]").forEach(input => input.disabled = true);

    // Masquer le bouton "Valider"
    submitBtnS.style.display = "none"; // On cache le bouton après soumission

    // Arrêter le timer quand le formulaire est soumis
    clearInterval(intervalS);
});
