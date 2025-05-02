const questionsReseaux = [
        {
            questionR: "1. Quel est le rôle principal des algorithmes sur les réseaux sociaux ?",
            optionsR: [
                "A. Supprimer les contenus inappropriés",
                "B. Empêcher les fake news",
                "C. Promouvoir les contenus éducatifs",
                "D. Maximiser notre temps d'écran"
            ],
            answerR: "D"
        },
        {
            questionR: "2. Qu’est-ce qu’une « bulle de filtre » ?",
            optionsR: [
                "A. Une zone privée de discussion",
                "B. Un mécanisme de modération automatique",
                "C. Un isolement algorithmique qui renforce nos opinions",
                "D. Un groupe fermé sur les réseaux"
            ],
            answerR: "C"
        },
        {
            questionR: "3. Pourquoi les réseaux sociaux sont-ils idéaux pour la manipulation de masse ?",
            optionsR: [
                "A. À cause de leur grande portée et ciblage précis",
                "B. Parce qu’ils offrent des abonnements payants",
                "C. Parce qu’ils sont utilisés principalement par des jeunes",
                "D. Car ils diffusent en direct"
            ],
            answerR: "A"
        },
        {
            questionR: "4. Que permet la collecte de nos likes, partages et clics ?",
            optionsR: [
                "A. Alimenter un profil comportemental",
                "B. Créer des animations personnalisées",
                "C. Gagner des badges de fidélité",
                "D. Supprimer les doublons de contenus"
            ],
            answerR: "A"
        },
        {
            questionR: "5. Qui peut exploiter les réseaux sociaux à des fins de manipulation ?",
            optionsR: [
                "A. Uniquement les influenceurs",
                "B. Seules les entreprises privées",
                "C. Aucun utilisateur individuel",
                "D. Tous les groupes d’intérêt, gouvernements ou entreprises"
            ],
            answerR: "D"
        },
        {
            questionR: "6. Que sont les deepfakes ?",
            optionsR: [
                "A. Des jeux vidéo immersifs",
                "B. Des faux profils utilisateurs",
                "C. Des contenus falsifiés (images/vidéos) pour tromper",
                "D. Des bugs d’affichage"
            ],
            answerR: "C"
        },
        {
            questionR: "7. Quel est l’effet principal des contenus trompeurs ?",
            optionsR: [
                "A. Amuser les utilisateurs",
                "B. Manipuler la perception du public",
                "C. Confondre les modérateurs",
                "D. Créer des vidéos virales"
            ],
            answerR: "B"
        },
        {
            questionR: "8. Que permet l’analyse massive de données sur les réseaux sociaux ?",
            optionsR: [
                "A. Adapter les messages à chaque personne",
                "B. Remplacer les journalistes",
                "C. Réduire le nombre d’utilisateurs inactifs",
                "D. Comprendre les tendances de mode"
            ],
            answerR: "A"
        },
        {
            questionR: "9. Quel est le danger principal de la personnalisation extrême des contenus ?",
            optionsR: [
                "A. Elle empêche de changer de mot de passe",
                "B. Elle favorise la désinformation ciblée",
                "C. Elle améliore les publicités",
                "D. Elle rend les réseaux plus rapides"
            ],
            answerR: "B"
        }
    ];

// Ajouter les questions dynamiquement
const containerReseaux = document.getElementById("quiz-form-reseau");
const questionContainerReseaux = document.getElementById("questions-container-reseau");

questionsReseaux.forEach((q, index) => {
    const blockR = document.createElement("div");
    blockR.className = "question-block-reseau";

    const qTitleR = document.createElement("p");
    qTitleR.innerHTML = `<strong>${q.questionR}</strong>`;
    blockR.appendChild(qTitleR);

    q.optionsR.forEach((option, i) => {
        const labelR = document.createElement("label");
        labelR.innerHTML = `<input type="radio" name="q${index + 1}" value="${option.charAt(0)}"> ${option}`;
        blockR.appendChild(labelR);
        blockR.appendChild(document.createElement("br"));
    });

    questionContainerReseaux.appendChild(blockR);
});

// Générer le bouton de soumission
const submitBtnR = document.createElement("button");
submitBtnR.type = "submit";
submitBtnR.id = "submitBtnR";
submitBtnR.textContent = "Valider mes réponses";
submitBtnR.style.marginTop = "20px";
containerReseaux.appendChild(submitBtnR); // On ajoute le bouton ici, après les questions

const timerElementR = document.getElementById("time-reseau");
let timeR = 120; // Temps en secondes (2 minutes)

// Démarrer le compte à rebours
let intervalR = setInterval(() => {
    timeR--; // Décrémenter le temps
    const minutesR = Math.floor(timeR / 60);
    const secondsR = timeR % 60;
    timerElementR.textContent = `${String(minutesR).padStart(2, '0')}:${String(secondsR).padStart(2, '0')}`;

    // Si le temps est écoulé
    if (timeR <= 0) {
        clearInterval(intervalR);
        alert("Temps écoulé ! Le formulaire sera soumis.");
        document.getElementById("quiz-form-reseau").submit(); // Soumettre le quiz automatiquement
    }
}, 1000);


// Gestion de la soumission du formulaire
document.getElementById("quiz-form-reseau").addEventListener("submit", function (e) {
    e.preventDefault(); // Empêcher la soumission classique

    let scoreR = 0;

    questionsReseaux.forEach((q, index) => {
        const selectedR = document.querySelector(`input[name="q${index + 1}"]:checked`);
        if (selectedR && selectedR.value === q.answerR) {
            scoreR++;
        }
    });

    let resultMessageR = "";
    if (scoreR >= 8) {
        resultMessageR = "🏆 Expert en cybersécurité !";
    } else if (scoreR >= 5) {
        resultMessageR = "⚠ Bon niveau, mais encore des points à améliorer.";
    } else {
        resultMessageR = "🚨 Attention ! Il est temps de renforcer vos connaissances.";
    }

    // Afficher le score final
    const scoreDivR = document.getElementById("score-reseau");
    scoreDivR.innerHTML = `<h2>Score final : ${scoreR}/10</h2><p>${resultMessageR}</p>`;
    scoreDivR.style.display = "block";

    // Désactiver les radios après la soumission
    document.querySelectorAll("input[type=radio]").forEach(input => input.disabled = true);

    // Masquer le bouton "Valider"
    submitBtnR.style.display = "none"; // On cache le bouton après soumission

    // Arrêter le timer quand le formulaire est soumis
    clearInterval(intervalR);
});