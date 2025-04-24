const questions = [
    {
        question: "1. Quel est le rôle principal des algorithmes sur les réseaux sociaux ?",
        options: [
            "A. Supprimer les contenus inappropriés",
            "B. Empêcher les fake news",
            "C. Promouvoir les contenus éducatifs",
            "D. Maximiser notre temps d'écran"
        ],
        answer: "D"
    },
    {
        question: "2. Qu’est-ce qu’une « bulle de filtre » ?",
        options: [
            "A. Une zone privée de discussion",
            "B. Un mécanisme de modération automatique",
            "C. Un isolement algorithmique qui renforce nos opinions",
            "D. Un groupe fermé sur les réseaux"
        ],
        answer: "C"
    },
    {
        question: "3. Pourquoi les réseaux sociaux sont-ils idéaux pour la manipulation de masse ?",
        options: [
            "A. À cause de leur grande portée et ciblage précis",
            "B. Parce qu’ils offrent des abonnements payants",
            "C. Parce qu’ils sont utilisés principalement par des jeunes",
            "D. Car ils diffusent en direct"
        ],
        answer: "A"
    },
    {
        question: "4. Que permet la collecte de nos likes, partages et clics ?",
        options: [
            "A. Alimenter un profil comportemental",
            "B. Créer des animations personnalisées",
            "C. Gagner des badges de fidélité",
            "D. Supprimer les doublons de contenus"
        ],
        answer: "A"
    },
    {
        question: "5. Qui peut exploiter les réseaux sociaux à des fins de manipulation ?",
        options: [
            "A. Uniquement les influenceurs",
            "B. Seules les entreprises privées",
            "C. Aucun utilisateur individuel",
            "D. Tous les groupes d’intérêt, gouvernements ou entreprises"
        ],
        answer: "D"
    },
    {
        question: "6. Que sont les deepfakes ?",
        options: [
            "A. Des jeux vidéo immersifs",
            "B. Des faux profils utilisateurs",
            "C. Des contenus falsifiés (images/vidéos) pour tromper",
            "D. Des bugs d’affichage"
        ],
        answer: "C"
    },
    {
        question: "7. Quel est l’effet principal des contenus trompeurs ?",
        options: [
            "A. Amuser les utilisateurs",
            "B. Manipuler la perception du public",
            "C. Confondre les modérateurs",
            "D. Créer des vidéos virales"
        ],
        answer: "B"
    },
    {
        question: "8. Que permet l’analyse massive de données sur les réseaux sociaux ?",
        options: [
            "A. Adapter les messages à chaque personne",
            "B. Remplacer les journalistes",
            "C. Réduire le nombre d’utilisateurs inactifs",
            "D. Comprendre les tendances de mode"
        ],
        answer: "A"
    },
    {
        question: "9. Quel est le danger principal de la personnalisation extrême des contenus ?",
        options: [
            "A. Elle empêche de changer de mot de passe",
            "B. Elle favorise la désinformation ciblée",
            "C. Elle améliore les publicités",
            "D. Elle rend les réseaux plus rapides"
        ],
        answer: "B"
    }
];

// Injection dynamique
const container = document.getElementById("quiz-form-reseau");
const questionContainer = document.getElementById("questions-container-reseau");

questions.forEach((q, index) => {
    const block = document.createElement("div");
    block.className = "question-block";

    const qTitle = document.createElement("p");
    qTitle.innerHTML = `<strong>${q.question}</strong>`;
    block.appendChild(qTitle);

    q.options.forEach(option => {
        const value = option.charAt(0);
        const label = document.createElement("label");
        label.innerHTML = `<input type="radio" name="q${index + 1}" value="${value}"> ${option}`;
        block.appendChild(label);
        block.appendChild(document.createElement("br"));
    });

    questionContainer.appendChild(block);
});

// Bouton "Valider"
const submitBtn = document.createElement("button");
submitBtn.type = "submit";
submitBtn.id = "submit-btn";
submitBtn.textContent = "Valider mes réponses";
submitBtn.style.marginTop = "20px";
container.appendChild(submitBtn);

// Timer
const timerElement = document.getElementById("time");
let time = 120;
let interval = setInterval(() => {
    time--;
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    timerElement.textContent = `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;

    if (time <= 0) {
        clearInterval(interval);
        alert("⏰ Temps écoulé ! Envoi automatique du quiz.");
        document.getElementById("quiz-form").dispatchEvent(new Event("submit"));
    }
}, 1000);

// Gestion soumission
document.getElementById("quiz-form-reseau").addEventListener("submit", function (e) {
    e.preventDefault();

    let score = 0;

    questions.forEach((q, index) => {
        const selected = document.querySelector(`input[name="q${index + 1}"]:checked`);
        if (selected && selected.value === q.answer) {
            score++;
        }
    });

    let message = "";
    if (score >= 8) {
        message = "🎉 Excellent ! Tu maîtrises vraiment bien le sujet.";
    } else if (score >= 5) {
        message = "👍 Pas mal ! Quelques notions à revoir.";
    } else {
        message = "❗ Oups... une révision s’impose.";
    }

    const scoreDiv = document.getElementById("score");
    scoreDiv.innerHTML = `<h2>Score final : ${score}/${questions.length}</h2><p>${message}</p>`;
    scoreDiv.style.display = "block";

    document.querySelectorAll("input[type=radio]").forEach(input => input.disabled = true);
    submitBtn.style.display = "none";
    clearInterval(interval);
});
